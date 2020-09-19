package com.bookaroom.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookaroom.entities.ReservationDTO;
import com.bookaroom.repositories.ReservationDAO;
import com.bookaroom.services.Reservations;

@Service
public class ReservationsImpl implements Reservations
{

    @Autowired
    ReservationDAO reservationDAO;

    @Override
    @Transactional
    public ReservationDTO addReservation(long listingId, long userId, Date from, Date to)
    {
        ReservationDTO newReservation = new ReservationDTO();
        newReservation.setListingId(listingId);
        newReservation.setUserId(userId);
        newReservation.setBeginDate(from);
        newReservation.setEndDate(to);
        reservationDAO.save(newReservation);

        return newReservation;

    }

    @Override
    @Transactional
    public List<ReservationDTO> findAll()
    {
        return reservationDAO.findAll();

    }

}
