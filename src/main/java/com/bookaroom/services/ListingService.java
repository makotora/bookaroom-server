package com.bookaroom.services;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.ListingDTO;
import com.bookaroom.enums.ListingType;
import com.bookaroom.exceptions.ListingNotFoundException;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.exceptions.UserNotAuthorizedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.web.dto.AvailabilityRange;
import com.bookaroom.web.dto.ListingResponse;

public interface ListingService
{

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
        List<AvailabilityRange> listingAvailabilities,
        MultipartFile mainPictureFile,
        List<MultipartFile> listingPictureFiles)
        throws ProvisioningException;

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
        List<AvailabilityRange> listingAvailabilities,
        MultipartFile mainPictureFile,
        List<MultipartFile> listingPictureFiles)
        throws ProvisioningException;

    public List<ListingDTO> findListing(
        String state,
        String city,
        String country,
        int people,
        Date inDate,
        Date outDate);

    public ListingDTO findById(long id)
        throws ListingNotFoundException;

    public ListingResponse getUserListingResponse(Long userId)
        throws UserNotFoundException, UserNotAuthorizedException, ListingNotFoundException;

    public void deleteByUser(Long userId)
        throws UserNotFoundException, UserNotAuthorizedException, ListingNotFoundException,
        ProvisioningException;
}