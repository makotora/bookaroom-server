package com.bookaroom.services;

import java.util.Date;
import java.util.List;

import com.bookaroom.entities.ReservationDTO;

public interface Reservations
{
    public ReservationDTO addReservation(long listingId, long userId, Date from, Date to);

    List<ReservationDTO> findAll();
}
