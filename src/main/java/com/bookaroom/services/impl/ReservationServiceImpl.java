package com.bookaroom.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookaroom.entities.ReservationDTO;
import com.bookaroom.repositories.ReservationDAO;
import com.bookaroom.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService
{

    @Autowired
    private ReservationDAO reservationDAO;

    @Override
    @Transactional
    public ReservationDTO addReservation(
        long listingId,
        long userId,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
    {
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
