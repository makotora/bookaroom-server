package com.bookaroom.services.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookaroom.entities.ListingAvailabilityDTO;
import com.bookaroom.entities.keys.ListingAvailabilityPK;
import com.bookaroom.exceptions.InvalidDateRangeException;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.repositories.ListingAvailabilityDAO;
import com.bookaroom.services.ListingAvailabilityService;
import com.bookaroom.util.Utils;
import com.bookaroom.web.dto.AvailabilityRange;

@Service
public class ListingAvailabilityServiceImpl implements ListingAvailabilityService
{
    private static Logger log = LoggerFactory.getLogger(ListingAvailabilityServiceImpl.class);

    @Autowired
    ListingAvailabilityDAO listingAvailabilityDAO;

    @Autowired
    ReservationServiceImpl reservationsService;

    @Override
    @Transactional
    public void addListingAvailability(long listingId, AvailabilityRange listingAvailability)
        throws ProvisioningException, InvalidDateRangeException
    {
        if (listingAvailability.getFrom().after(listingAvailability.getTo())) {
            throw new ProvisioningException("Listing availability start date cannot be after the availability end date");
        }

        final Date from = listingAvailability.getFrom();
        final Date to = listingAvailability.getTo();

        for (Date currentDate : Utils.getAllDatesFromTo(from, to)) {
            addListingAvailability(listingId, currentDate);
        }
    }

    @Override
    @Transactional
    public void addListingAvailability(long listingId, Date listingAvailabilityDate)
        throws ProvisioningException
    {
        ListingAvailabilityDTO newListingAvailability = new ListingAvailabilityDTO();
        newListingAvailability.setId(new ListingAvailabilityPK(listingId, listingAvailabilityDate));
        listingAvailabilityDAO.save(newListingAvailability);
    }

    @Override
    public List<ListingAvailabilityDTO> getListingAvailabilities(Long listingId)
    {
        return listingAvailabilityDAO.findByIdListingId(listingId);
    }

    @Override
    public List<ListingAvailabilityDTO> getListingAvailabilitiesOrderedAscending(Long listingId)
    {
        return listingAvailabilityDAO.findByIdListingIdOrderByIdDateAsc(listingId);
    }

    @Override
    @Transactional
    public void deleteAllByListingId(Long listingId)
    {
        listingAvailabilityDAO.deleteByListingId(listingId);
    }

}
