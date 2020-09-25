package com.bookaroom.services;

import java.util.Date;
import java.util.List;

import com.bookaroom.entities.ReservationDTO;

public interface ReservationService
{
    public ReservationDTO addReservation(
        long listingId,
        long userId,
        Date from,
        Date to,
        Integer numberOfGuests);

    public boolean hasLastReservationPassed(Long userId, Long listingId, Date date);

    List<ReservationDTO> findAll();
}
