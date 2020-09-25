package com.bookaroom.services;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.bookaroom.entities.ReservationDTO;
import com.bookaroom.exceptions.ListingNotAvailableException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;

public interface ReservationService
{
    public ReservationDTO addReservation(
        Principal principal,
        long listingId,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
        throws UserNotFoundException, UserNotAuthenticatedException, ListingNotAvailableException;

    public ReservationDTO addReservation(
        long listingId,
        long userId,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
        throws ListingNotAvailableException;

    public boolean hasLastReservationPassed(Long userId, Long listingId, Date date);

    List<ReservationDTO> findAll();
}
