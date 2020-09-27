package com.bookaroom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.ListingReviewDTO;

@Repository
public interface ListingReviewDAO
    extends
        JpaRepository<ListingReviewDTO, Long>,
        ListingReviewDAOCustom
{
    public List<ListingReviewDTO> findByListingIdAndUserId(Long listingId, Long userId);

    @Query("SELECT r FROM ListingReviewDTO r, UserDTO u WHERE u.listingId = r.listingId AND u.id = :hostUserId")
    public List<ListingReviewDTO> findByHostUserId(@Param("hostUserId") Long hostUserId);
}
