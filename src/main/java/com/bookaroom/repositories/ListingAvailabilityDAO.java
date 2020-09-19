package com.bookaroom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.ListingAvailabilityDTO;

@Repository
public interface ListingAvailabilityDAO extends JpaRepository<ListingAvailabilityDTO, Long>
{
    public List<ListingAvailabilityDTO> findByIdListingIdOrderByIdDateAsc(Long listingId);

    public List<ListingAvailabilityDTO> findByIdListingId(Long listingId);

    // @formatter:off
    @Query("DELETE "
           + " FROM ListingAvailabilityDTO la"
           + " WHERE la.id.listingId = :listingId")
    // @formatter:on
    @Modifying
    public void deleteByListingId(@Param("listingId") Long listingId);
}
