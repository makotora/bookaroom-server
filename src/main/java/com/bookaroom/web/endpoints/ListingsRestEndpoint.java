package com.bookaroom.web.endpoints;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.UserDTO;
import com.bookaroom.enums.ListingType;
import com.bookaroom.exceptions.ListingNotFoundException;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotAuthorizedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.services.ListingService;
import com.bookaroom.services.UserService;
import com.bookaroom.util.Constants;
import com.bookaroom.web.dto.ActionResponse;
import com.bookaroom.web.dto.AvailabilityRange;
import com.bookaroom.web.dto.ListingResponse;

@RestController
@RequestMapping("listings")
public class ListingsRestEndpoint
{

    private static Logger log = LoggerFactory.getLogger(ListingsRestEndpoint.class);

    @Autowired
    private ListingService listings;

    @Autowired
    private UserService userService;

    @RequestMapping(
        value = "/create",
        method = RequestMethod.POST,
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ActionResponse create(
        Principal principal,
        @RequestPart(name = "address", required = true) String address,
        @RequestParam(name = "longitude", required = false) Double longitude,
        @RequestParam(name = "latitude", required = false) Double latitude,
        @RequestParam(name = "maxGuests", required = true) Integer maxGuests,
        @RequestParam(name = "minPrice", required = true) Double minPrice,
        @RequestParam(name = "costPerExtraGuest", required = true) Double costPerExtraGuest,
        @RequestPart(name = "typeStr", required = true) String typeStr,
        @RequestPart(name = "rules", required = false) String rules,
        @RequestPart(name = "description", required = true) String description,
        @RequestParam(name = "numberOfBeds", required = true) Integer numberOfBeds,
        @RequestParam(name = "numberOfBathrooms", required = true) Integer numberOfBathrooms,
        @RequestParam(name = "numberOfBedrooms", required = true) Integer numberOfBedrooms,
        @RequestParam(name = "area", required = true) Integer area,
        @RequestParam(name = "hasLivingRoom", required = true) Boolean hasLivingRoom,
        @RequestParam(name = "listingAvailabilities") String listingAvailabilitiesStr,
        @RequestParam(name = "mainListingPictureFile", required = false) MultipartFile mainListingPictureFile,
        @RequestParam(name = "listingPictureFiles") ArrayList<MultipartFile> listingPictureFiles)
    {
        log.info("Got request: "
                 + "address="
                 + address
                 + ", "
                 + "longitude="
                 + longitude
                 + ", "
                 + "latitude="
                 + latitude
                 + ", "
                 + "maxGuests="
                 + maxGuests
                 + ", "
                 + "minPrice="
                 + minPrice
                 + ", "
                 + "costPerExtraGuest="
                 + costPerExtraGuest
                 + ", "
                 + "typeStr="
                 + typeStr
                 + ", "
                 + "rules="
                 + rules
                 + ", "
                 + "description="
                 + description
                 + ", "
                 + "numberOfBeds="
                 + numberOfBeds
                 + ", "
                 + "numberOfBathrooms="
                 + numberOfBathrooms
                 + ", "
                 + "numberOfBedrooms="
                 + numberOfBedrooms
                 + ", "
                 + "area="
                 + area
                 + ", "
                 + "hasLivingRoom="
                 + hasLivingRoom
                 + ", listingAvailabilitiesStr="
                 + listingAvailabilitiesStr);

        UserDTO user;
        try {
            user = userService.findAndAuthorizeByPrincipal(principal, Constants.HOST_ROLE);
        }
        catch (UserNotAuthenticatedException e) {
            return new ActionResponse(false, "You are not authenticated");
        }
        catch (UserNotFoundException e) {
            return new ActionResponse(false, "User not found");
        }
        catch (UserNotAuthorizedException e) {
            return new ActionResponse(false, "Action is not allowed. Only hosts can create listings");
        }

        List<AvailabilityRange> listingAvailabilities = parseListingAvailabilitiesRequestString(listingAvailabilitiesStr);

        ListingType type = null;
        try {
            type = ListingType.valueOf(typeStr);
        }
        catch (Exception e) {
            return new ActionResponse(false, "Invalid listing type");
        }

        try {
            listings.create(user.getId(),
                            address,
                            longitude,
                            latitude,
                            maxGuests,
                            minPrice,
                            costPerExtraGuest,
                            type,
                            rules,
                            description,
                            numberOfBeds,
                            numberOfBathrooms,
                            numberOfBedrooms,
                            area,
                            hasLivingRoom,
                            listingAvailabilities,
                            mainListingPictureFile,
                            listingPictureFiles);
        }
        catch (ProvisioningException e) {
            return new ActionResponse(false, e.getMessage());
        }

        return new ActionResponse(true, "Listing added successfully");
    }

    @RequestMapping(
        value = "/update",
        method = RequestMethod.POST,
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ActionResponse update(
        Principal principal,
        @RequestPart(name = "address", required = true) String address,
        @RequestParam(name = "longitude", required = false) Double longitude,
        @RequestParam(name = "latitude", required = false) Double latitude,
        @RequestParam(name = "maxGuests", required = true) Integer maxGuests,
        @RequestParam(name = "minPrice", required = true) Double minPrice,
        @RequestParam(name = "costPerExtraGuest", required = true) Double costPerExtraGuest,
        @RequestPart(name = "typeStr", required = true) String typeStr,
        @RequestPart(name = "rules", required = false) String rules,
        @RequestPart(name = "description", required = true) String description,
        @RequestParam(name = "numberOfBeds", required = true) Integer numberOfBeds,
        @RequestParam(name = "numberOfBathrooms", required = true) Integer numberOfBathrooms,
        @RequestParam(name = "numberOfBedrooms", required = true) Integer numberOfBedrooms,
        @RequestParam(name = "area", required = true) Integer area,
        @RequestParam(name = "hasLivingRoom", required = true) Boolean hasLivingRoom,
        @RequestParam(name = "listingAvailabilities") String listingAvailabilitiesStr,
        @RequestParam(name = "mainListingPictureFile", required = false) MultipartFile mainListingPictureFile,
        @RequestParam(name = "listingPictureFiles") ArrayList<MultipartFile> listingPictureFiles)
    {
        log.info("Got request: "
                 + "address="
                 + address
                 + ", "
                 + "longitude="
                 + longitude
                 + ", "
                 + "latitude="
                 + latitude
                 + ", "
                 + "maxGuests="
                 + maxGuests
                 + ", "
                 + "minPrice="
                 + minPrice
                 + ", "
                 + "costPerExtraGuest="
                 + costPerExtraGuest
                 + ", "
                 + "typeStr="
                 + typeStr
                 + ", "
                 + "rules="
                 + rules
                 + ", "
                 + "description="
                 + description
                 + ", "
                 + "numberOfBeds="
                 + numberOfBeds
                 + ", "
                 + "numberOfBathrooms="
                 + numberOfBathrooms
                 + ", "
                 + "numberOfBedrooms="
                 + numberOfBedrooms
                 + ", "
                 + "area="
                 + area
                 + ", "
                 + "hasLivingRoom="
                 + hasLivingRoom
                 + ", listingAvailabilitiesStr="
                 + listingAvailabilitiesStr);

        UserDTO user;
        try {
            user = userService.findAndAuthorizeByPrincipal(principal, Constants.HOST_ROLE);
        }
        catch (UserNotAuthenticatedException e) {
            return new ActionResponse(false, "You are not authenticated");
        }
        catch (UserNotFoundException e) {
            return new ActionResponse(false, "User not found");
        }
        catch (UserNotAuthorizedException e) {
            return new ActionResponse(false, "Action is not allowed. Only hosts can create listings");
        }

        List<AvailabilityRange> listingAvailabilities = parseListingAvailabilitiesRequestString(listingAvailabilitiesStr);

        ListingType type = null;
        try {
            type = ListingType.valueOf(typeStr);
        }
        catch (Exception e) {
            return new ActionResponse(false, "Invalid listing type");
        }

        try {
            listings.update(user.getId(),
                            address,
                            longitude,
                            latitude,
                            maxGuests,
                            minPrice,
                            costPerExtraGuest,
                            type,
                            rules,
                            description,
                            numberOfBeds,
                            numberOfBathrooms,
                            numberOfBedrooms,
                            area,
                            hasLivingRoom,
                            listingAvailabilities,
                            mainListingPictureFile,
                            listingPictureFiles);
        }
        catch (ProvisioningException e) {
            return new ActionResponse(false, e.getMessage());
        }

        return new ActionResponse(true, "Listing updated successfully");
    }

    private List<AvailabilityRange> parseListingAvailabilitiesRequestString(String listingAvailabilitiesStr)
    {
        List<AvailabilityRange> listingAvailabilities = new ArrayList<AvailabilityRange>();
        for (String listingAvailabilityRequestStr : listingAvailabilitiesStr.split(";")) {
            listingAvailabilities.add(AvailabilityRange.fromRequestString(listingAvailabilityRequestStr));
        }

        return listingAvailabilities;
    }

    @RequestMapping(path = "/getByCurrentUser", method = RequestMethod.GET)
    public ListingResponse getByCurrentUser(Principal principal)
    {
        try {
            UserDTO user = userService.findAndAuthorizeByPrincipal(principal, Constants.HOST_ROLE);
            return listings.getUserListingResponse(user.getId());
        }
        catch (UserNotFoundException
            | UserNotAuthorizedException
            | ListingNotFoundException
            | UserNotAuthenticatedException e) {
            if (Constants.DEBUG) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @RequestMapping(path = "/deleteByCurrentUser", method = RequestMethod.DELETE)
    public ActionResponse deleteByCurrentUser(Principal principal)
    {
        try {
            UserDTO user = userService.findAndAuthorizeByPrincipal(principal, Constants.HOST_ROLE);
            listings.deleteByUser(user.getId());
        }
        catch (UserNotFoundException e) {
            if (Constants.DEBUG) {
                e.printStackTrace();
            }

            return getFailureResponse("User not found");
        }
        catch (UserNotAuthenticatedException e) {
            if (Constants.DEBUG) {
                e.printStackTrace();
            }

            return getFailureResponse("User not authenticated");
        }
        catch (UserNotAuthorizedException e) {
            if (Constants.DEBUG) {
                e.printStackTrace();
            }

            return getFailureResponse("User not authorized");
        }
        catch (ListingNotFoundException e) {
            if (Constants.DEBUG) {
                e.printStackTrace();
            }

            return getFailureResponse("Listing not found");
        }
        catch (ProvisioningException e) {
            if (Constants.DEBUG) {
                e.printStackTrace();
            }

            return getFailureResponse(e.getMessage());
        }

        return new ActionResponse(true, "Listing was successfully deleted");
    }

    private ActionResponse getFailureResponse(String message)
    {
        return new ActionResponse(false, message);
    }

}
