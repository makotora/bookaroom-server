package com.bookaroom.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.ReservationDTO;

@Repository
public interface ReservationDAO extends JpaRepository<ReservationDTO, Long>
{
    public List<ReservationDTO> findByListingId(Long listingId);

    public List<ReservationDTO> findByUserId(Long userId);

    // @formatter:off
    @Query("  SELECT r"
         + "  FROM ReservationDTO r"
         + "  WHERE r.userId = :userId"
         + "  AND r.listingId = :listingId"
         + "  AND r.checkOut <= :date"
         + "  AND NOT EXISTS (SELECT r2 FROM ReservationDTO r2 WHERE r2.userId = r.userId AND r2.listingId = r.listingId AND r2.checkIn > r.checkIn)")
    // @formatter:on
    public List<ReservationDTO> findLastReservationIfPassed(
        @Param("userId") Long userId,
        @Param("listingId") Long listingId,
        @Param("date") Date date);
}
