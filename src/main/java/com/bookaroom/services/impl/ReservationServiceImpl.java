package com.bookaroom.services.impl;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookaroom.entities.ReservationDTO;
import com.bookaroom.entities.UserDTO;
import com.bookaroom.exceptions.ListingNotAvailableException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.repositories.ReservationDAO;
import com.bookaroom.services.ListingService;
import com.bookaroom.services.ReservationService;
import com.bookaroom.services.UserService;

@Service
public class ReservationServiceImpl implements ReservationService
{

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private UserService users;

    @Autowired
    private ListingService listings;

    @Override
    @Transactional
    public ReservationDTO addReservation(
        Principal principal,
        long listingId,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
        throws UserNotFoundException, UserNotAuthenticatedException, ListingNotAvailableException
    {
        UserDTO user = users.findByPrincipal(principal);

        return addReservation(listingId, user.getId(), checkIn, checkOut, numberOfGuests);
    }

    @Override
    @Transactional
    public ReservationDTO addReservation(
        long listingId,
        long userId,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
        throws ListingNotAvailableException
    {
        if (!listings.isAvailableOnDates(listingId, checkIn, checkOut)) {
            throw new ListingNotAvailableException();
        }

        ReservationDTO newReservation = new ReservationDTO();
        newReservation.setListingId(listingId);
        newReservation.setUserId(userId);
        newReservation.setCheckIn(checkIn);
        newReservation.setCheckOut(checkOut);
        newReservation.setNumberOfGuests(numberOfGuests);
        reservationDAO.save(newReservation);

        return newReservation;

    }

    @Override
    @Transactional
    public List<ReservationDTO> findAll()
    {
        return reservationDAO.findAll();

    }

    @Override
    public boolean hasLastReservationPassed(Long userId, Long listingId, Date date)
    {
        List<ReservationDTO> lastReservationPassed = reservationDAO.findLastReservationIfPassed(userId,
                                                                                                listingId,
                                                                                                date);

        return lastReservationPassed != null && !lastReservationPassed.isEmpty();
    }

}
