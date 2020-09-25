package com.bookaroom.services.impl;

import java.math.BigInteger;
import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.FileUploadDTO;
import com.bookaroom.entities.ListingAvailabilityDTO;
import com.bookaroom.entities.ListingDTO;
import com.bookaroom.entities.UserDTO;
import com.bookaroom.enums.ListingType;
import com.bookaroom.exceptions.InvalidDateRangeException;
import com.bookaroom.exceptions.ListingNotFoundException;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotAuthorizedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.repositories.ListingDAO;
import com.bookaroom.search.SpecificationsBuilder;
import com.bookaroom.services.FileUploadService;
import com.bookaroom.services.ListingAvailabilityService;
import com.bookaroom.services.ListingPictureService;
import com.bookaroom.services.ListingService;
import com.bookaroom.services.ReservationService;
import com.bookaroom.services.SearchService;
import com.bookaroom.services.UserService;
import com.bookaroom.util.Constants;
import com.bookaroom.util.Utils;
import com.bookaroom.web.dto.AvailabilityRange;
import com.bookaroom.web.dto.ListingFullViewResponse;
import com.bookaroom.web.dto.ListingResponse;
import com.bookaroom.web.dto.ListingShortViewResponse;

@Service("Listings")
public class ListingServiceImpl implements ListingService
{
    private static Logger log = LoggerFactory.getLogger(ListingServiceImpl.class);

    @Autowired
    private ListingDAO listingDAO;

    @Autowired
    private ListingAvailabilityService listingAvailabilities;

    @Autowired
    private FileUploadService fileUploads;

    @Autowired
    private ListingPictureService listingPictures;

    @Autowired
    private UserService users;

    @Autowired
    private SearchService searches;

    @Autowired
    private ReservationService reservations;

    @Transactional
    @Override
    public ListingDTO create(
        Long userId,
        String address,
        Double longitude,
        Double latitude,
        Integer maxGuests,
        Double minPrice,
        Double costPerExtraGuest,
        ListingType type,
        String rules,
        String description,
        Integer numberOfBeds,
        Integer numberOfBathrooms,
        Integer numberOfBedrooms,
        Integer area,
        boolean hasLivingRoom,
        List<AvailabilityRange> listingAvailabilityRanges,
        MultipartFile mainPictureFile,
        List<MultipartFile> listingPictureFiles)
        throws ProvisioningException
    {
        UserDTO user = findAndAuthenticateUser(userId);

        if (user.getListingId() != null) {
            throw new ProvisioningException("Host has already created a listing");
        }

        ListingDTO listing = new ListingDTO();
        listing.setAddress(address);
        listing.setLongitude(longitude);
        listing.setLatitude(latitude);
        listing.setMaxGuests(maxGuests);
        listing.setMinPrice(minPrice);
        listing.setCostPerExtraGuest(costPerExtraGuest);
        listing.setType(type);
        listing.setRules(rules);
        listing.setDescription(description);
        listing.setNumberOfBeds(numberOfBeds);
        listing.setNumberOfBathrooms(numberOfBathrooms);
        listing.setNumberOfBedrooms(numberOfBedrooms);
        listing.setArea(area);
        listing.setHasLivingRoom(hasLivingRoom);

        listingDAO.saveAndFlush(listing);
        Long listingId = listing.getId();

        validateAvailabilityRanges(listingAvailabilityRanges);

        for (Date availabilityDate : getDistinctAvailabilityDates(listingAvailabilityRanges)) {
            listingAvailabilities.addListingAvailability(listingId, availabilityDate);
        }

        for (MultipartFile listingPictureFile : listingPictureFiles) {
            listingPictures.add(listingId, listingPictureFile);
        }

        String mainListingPictureName = listingId.toString();
        FileUploadDTO mainPictureFileUpload = fileUploads.uploadFile(mainPictureFile,
                                                                     Constants.LISTING_PICTURES_DIRECTORY,
                                                                     mainListingPictureName);

        listing.setMainPictureFileUploadId(mainPictureFileUpload.getId());
        listingDAO.save(listing);

        try {
            users.setListingId(userId, listingId);
        }
        catch (UserNotFoundException e) {
            throw new ProvisioningException("Unknown user ID");
        }

        return listing;
    }

    @Transactional
    @Override
    public ListingDTO update(
        Long userId,
        String address,
        Double longitude,
        Double latitude,
        Integer maxGuests,
        Double minPrice,
        Double costPerExtraGuest,
        ListingType type,
        String rules,
        String description,
        Integer numberOfBeds,
        Integer numberOfBathrooms,
        Integer numberOfBedrooms,
        Integer area,
        boolean hasLivingRoom,
        List<AvailabilityRange> listingAvailabilityRanges,
        MultipartFile mainPictureFile,
        List<MultipartFile> listingPictureFiles)
        throws ProvisioningException
    {
        UserDTO user = findAndAuthenticateUser(userId);

        final Long listingId = user.getListingId();

        if (listingId == null) {
            throw new ProvisioningException("User has no listing to delete");
        }

        ListingDTO listing;
        try {
            listing = findById(listingId);
        }
        catch (ListingNotFoundException e1) {
            throw new ProvisioningException("Listing not found");
        }

        listing.setAddress(address);
        listing.setLongitude(longitude);
        listing.setLatitude(latitude);
        listing.setMaxGuests(maxGuests);
        listing.setMinPrice(minPrice);
        listing.setCostPerExtraGuest(costPerExtraGuest);
        listing.setType(type);
        listing.setRules(rules);
        listing.setDescription(description);
        listing.setNumberOfBeds(numberOfBeds);
        listing.setNumberOfBathrooms(numberOfBathrooms);
        listing.setNumberOfBedrooms(numberOfBedrooms);
        listing.setArea(area);
        listing.setHasLivingRoom(hasLivingRoom);

        validateAvailabilityRanges(listingAvailabilityRanges);

        listingAvailabilities.deleteAllByListingId(listingId);
        for (Date availabilityDate : getDistinctAvailabilityDates(listingAvailabilityRanges)) {
            listingAvailabilities.addListingAvailability(listingId, availabilityDate);
        }

        listingPictures.deleteAllByListingId(listingId);
        for (MultipartFile listingPictureFile : listingPictureFiles) {
            listingPictures.add(listingId, listingPictureFile);
        }

        if (listing.getMainPictureFileUploadId() != null) {
            fileUploads.deleteFile(listing.getMainPictureFileUploadId());
        }

        String mainListingPictureName = listingId.toString();
        FileUploadDTO mainPictureFileUpload = fileUploads.uploadFile(mainPictureFile,
                                                                     Constants.LISTING_PICTURES_DIRECTORY,
                                                                     mainListingPictureName);

        listing.setMainPictureFileUploadId(mainPictureFileUpload.getId());
        listingDAO.saveAndFlush(listing);

        return listing;
    }

    private void validateAvailabilityRanges(List<AvailabilityRange> listingAvailabilityRanges)
        throws ProvisioningException
    {
        for (AvailabilityRange availabilityRange : listingAvailabilityRanges) {
            if (!availabilityRange.getFrom().before(availabilityRange.getTo())) {
                throw new ProvisioningException("Listing availability from date must be before the to date");
            }
        }
    }

    private UserDTO findAndAuthenticateUser(Long userId)
        throws ProvisioningException
    {
        try {
            return users.findAndAuthorizeById(userId, Constants.HOST_ROLE);
        }
        catch (UserNotFoundException e) {
            throw new ProvisioningException("User not found");
        }
        catch (UserNotAuthorizedException e) {
            throw new ProvisioningException("Only hosts can create or update listings");
        }
    }

    private Set<Date> getDistinctAvailabilityDates(List<AvailabilityRange> listingAvailabilityRanges)
        throws ProvisioningException
    {
        Set<Date> distinctAvailabilityDates = new HashSet<Date>();

        for (AvailabilityRange availabilityRange : listingAvailabilityRanges) {
            try {
                distinctAvailabilityDates.addAll(Utils.getAllDatesFromTo(availabilityRange.getFrom(),
                                                                         availabilityRange.getTo()));
            }
            catch (InvalidDateRangeException e) {
                throw new ProvisioningException("Listing availability start date cannot be after the availability end date");
            }
        }

        return distinctAvailabilityDates;
    }

    @Override
    public List<ListingDTO> advancedSearch(
        String state,
        String city,
        String country,
        int people)
    {
        log.info("START findListing");
        SpecificationsBuilder specsBuilder = new SpecificationsBuilder();

        if (!state.equals("")) {
            specsBuilder.with("state", ":", state);
        }

        if (!city.equals("")) {
            specsBuilder.with("city", ":", city);
        }

        if (!country.equals("")) {
            specsBuilder.with("country", ":", country);
        }

        specsBuilder.with("accommodates", ">=", people);

        List<ListingDTO> matchingListings = null;
        Specification<ListingDTO> specs = specsBuilder.build();
        try {
            matchingListings = listingDAO.findAll(specs);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(matchingListings,
                         (l1, l2) -> getListingTotalPrice(l1, people).compareTo(getListingTotalPrice(l2, people)));

        return matchingListings;

    }

    private Double getListingTotalPrice(ListingDTO listing, Integer numberOfGuests)
    {
        return Utils.roundDouble((listing.getMinPrice() == null ? 0 : listing.getMinPrice())
                                 + (listing.getCostPerExtraGuest() == null ? 0
                                                                           : listing.getCostPerExtraGuest())
                                   * (numberOfGuests == null ? 1 : numberOfGuests),
                                 2);
    }

    @Override
    public ListingDTO findById(Long id)
        throws ListingNotFoundException
    {
        ListingDTO listing = listingDAO.findOne(id);
        if (listing == null)
            throw new ListingNotFoundException();

        return listing;
    }

    public List<ListingDTO> findAllListings()
    {
        return listingDAO.findAll();
    }

    @Override
    public ListingResponse getUserListingResponse(Long userId)
        throws UserNotFoundException, UserNotAuthorizedException, ListingNotFoundException
    {
        UserDTO user = users.findAndAuthorizeById(userId, Constants.HOST_ROLE);

        if (user.getListingId() == null) {
            return null;
        }

        final Long listingId = user.getListingId();

        ListingDTO userListing = findById(listingId);

        List<ListingAvailabilityDTO> listingAvailabilitiesOrdered = listingAvailabilities.getListingAvailabilities(listingId);
        List<AvailabilityRange> listingAvailabilityRanges = Utils.buildAvailabilityRanges(listingAvailabilitiesOrdered.stream()
                                                                                                                      .map(av -> av.getId()
                                                                                                                                   .getDate())
                                                                                                                      .collect(Collectors.toList()));

        FileUploadDTO mainPictureFile = fileUploads.findById(userListing.getMainPictureFileUploadId());
        String mainImagePath = mainPictureFile == null ? null
                                                       : Utils.prepareListingPicturePath(mainPictureFile.getServerPath());
        List<String> additionalPicturePaths = fileUploads.findListingPictureFiles(listingId)
                                                         .stream()
                                                         .map(fo -> Utils.prepareListingPicturePath(fo.getServerPath()))
                                                         .collect(Collectors.toList());

        return new ListingResponse(userListing.getId(),
                                   userListing.getAddress(),
                                   userListing.getLongitude(),
                                   userListing.getLatitude(),
                                   userListing.getMaxGuests(),
                                   userListing.getMinPrice(),
                                   userListing.getCostPerExtraGuest(),
                                   userListing.getType().name(),
                                   userListing.getRules(),
                                   userListing.getDescription(),
                                   userListing.getNumberOfBeds(),
                                   userListing.getNumberOfBathrooms(),
                                   userListing.getNumberOfBedrooms(),
                                   userListing.getArea(),
                                   userListing.isHasLivingRoom(),
                                   listingAvailabilityRanges,
                                   mainImagePath,
                                   additionalPicturePaths);
    }

    @Override
    public void deleteByUser(Long userId)
        throws UserNotFoundException, UserNotAuthorizedException, ListingNotFoundException,
        ProvisioningException
    {
        UserDTO user = users.findAndAuthorizeById(userId, Constants.HOST_ROLE);

        if (user.getListingId() == null) {
            throw new ListingNotFoundException();
        }

        final Long listingId = user.getListingId();

        ListingDTO userListing = findById(listingId);

        listingAvailabilities.deleteAllByListingId(listingId);
        fileUploads.deleteFile(userListing.getMainPictureFileUploadId());
        listingPictures.deleteAllByListingId(listingId);

        users.setListingId(userId, null);

        listingDAO.delete(userListing);
    }

    @Override
    public List<ListingShortViewResponse> findRecommendededListingByPrincipal(Principal principal) throws UserNotFoundException, UserNotAuthenticatedException
    {
        UserDTO user = users.findByPrincipal(principal);
        
        return getListingShortViewList(listingDAO.findUserRecommendedShortViews(user.getId(),
                                                                                Constants.MAX_DAYS_AFTER_SEARCH,
                                                                                Constants.MAX_RECOMMENDATIONS));
    }

    @Override
    public List<ListingShortViewResponse> searchByPrincipal(
        Principal principal,
        String state,
        String city,
        String country,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
        throws UserNotFoundException, UserNotAuthenticatedException
    {
        UserDTO user = users.findByPrincipal(principal);
        
        searches.saveSearch(user.getId(), state, city, country, checkIn, checkOut, numberOfGuests);

        return getListingShortViewList(listingDAO.searchShortViews(state,
                                                                   city,
                                                                   country,
                                                                   checkIn,
                                                                   checkOut,
                                                                   numberOfGuests));
    }

    private List<ListingShortViewResponse> getListingShortViewList(List<Object[]> rows)
    {
        return rows.stream().map(cols -> getListingShortViewObject(cols)).collect(Collectors.toList());
    }

    private ListingShortViewResponse getListingShortViewObject(Object[] cols)
    {
        return new ListingShortViewResponse(((BigInteger) cols[0]).longValue(),
                                            (String) cols[1],
                                            cols[2] == null ? null : Utils.roundDouble((Double) cols[2], 2),
                                            Constants.DEFAULT_CURRENCY,
                                            cols[3] == null ? null
                                                            : Utils.prepareListingPicturePath((String) cols[3]),
                                            cols[4] == null ? null : (Double) cols[4]);
    }

    @Override
    public ListingFullViewResponse view(
        Principal principal,
        Long listingId,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
        throws ListingNotFoundException, UserNotFoundException, UserNotAuthenticatedException
    {
        UserDTO user = users.findByPrincipal(principal);
        
        ListingDTO listing = findById(listingId);

        FileUploadDTO mainPictureFile = null;
        if (listing.getMainPictureFileUploadId() != null) {
            mainPictureFile = fileUploads.findById(listing.getMainPictureFileUploadId());
        }

        String mainPicturePath = mainPictureFile == null ? null
                                                       : Utils.prepareListingPicturePath(mainPictureFile.getServerPath());
        List<String> additionalPicturePaths = fileUploads.findListingPictureFiles(listingId)
                                                         .stream()
                                                         .map(fo -> Utils.prepareListingPicturePath(fo.getServerPath()))
                                                         .collect(Collectors.toList());
        
        UserDTO host = users.findByListingId(listingId);
        FileUploadDTO hostMainPictureFile = fileUploads.findById(host.getPictureFileUploadId());

        String hostPicturePath = Utils.prepareUserPicturePath(hostMainPictureFile.getServerPath());
        
        boolean hasLastReservationPassed = reservations.hasLastReservationPassed(user.getId(),
                                                                                 listingId,
                                                                                 new Date());

        boolean isAvailable = (checkIn != null && checkOut != null) ? (numberOfGuests == null
                                                                       || numberOfGuests <= listing.getMaxGuests())
                                                                      && isAvailableOnDates(listingId,
                                                                                            checkIn,
                                                                                            checkOut)
                                                                    : false;
                                                                      
        Double totalPrice = getListingTotalPrice(listing, numberOfGuests);

        return new ListingFullViewResponse(listing.getId(),
                                           listing.getAddress(),
                                           listing.getLongitude(),
                                           listing.getLatitude(),
                                           listing.getMaxGuests(),
                                           listing.getMinPrice(),
                                           listing.getCostPerExtraGuest(),
                                           listing.getType().name(),
                                           listing.getRules(),
                                           listing.getDescription(),
                                           listing.getNumberOfBeds(),
                                           listing.getNumberOfBathrooms(),
                                           listing.getNumberOfBedrooms(),
                                           listing.getArea(),
                                           listing.isHasLivingRoom(),
                                           totalPrice,
                                           Constants.DEFAULT_CURRENCY,
                                           mainPicturePath,
                                           additionalPicturePaths,
                                           host.getId(),
                                           hostPicturePath,
                                           isAvailable,
                                           hasLastReservationPassed);
    }

    @Override
    public boolean isAvailableOnDates(Long listingId, Date checkIn, Date checkOut)
    {
        return listingDAO.isAvailableOnDates(listingId, checkIn, checkOut);
    }

}
