package com.bookaroom.services;

import java.util.Date;
import java.util.List;

import com.bookaroom.entities.ListingAvailabilityDTO;
import com.bookaroom.exceptions.InvalidDateRangeException;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.web.dto.AvailabilityRange;

public interface ListingAvailabilityService
{
    public void addListingAvailability(long listingId, AvailabilityRange listingAvailability)
        throws ProvisioningException, InvalidDateRangeException;

    public void addListingAvailability(long listingId, Date listingAvailabilityDate)
        throws ProvisioningException;

    public List<ListingAvailabilityDTO> getListingAvailabilities(Long listingId);

    public List<ListingAvailabilityDTO> getListingAvailabilitiesOrderedAscending(Long listingId);

    public void deleteAllByListingId(Long listingId);

}
