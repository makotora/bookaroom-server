package com.bookaroom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.ReservationDTO;

@Repository
public interface ReservationDAO extends JpaRepository<ReservationDTO, Long>
{
    public List<ReservationDTO> findByListingId(long listingId);

    public List<ReservationDTO> findByUserId(long userId);
}
