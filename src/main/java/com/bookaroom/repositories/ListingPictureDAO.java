package com.bookaroom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.ListingPictureDTO;

@Repository
public interface ListingPictureDAO extends JpaRepository<ListingPictureDTO, Long>
{
    public ListingPictureDTO findById(Long id);

    public List<ListingPictureDTO> findByListingId(Long listingId);

    public Long countByListingId(Long listingId);
}
