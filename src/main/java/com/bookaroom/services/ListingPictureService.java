package com.bookaroom.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.ListingPictureDTO;
import com.bookaroom.exceptions.EntityNotFoundException;
import com.bookaroom.exceptions.ProvisioningException;

public interface ListingPictureService
{
    public ListingPictureDTO add(Long listingId, MultipartFile file)
        throws ProvisioningException;

    public List<ListingPictureDTO> findByListingId(Long listingId);

    public ListingPictureDTO findById(long id)
        throws EntityNotFoundException;

    public void delete(Long id)
        throws ProvisioningException;

    public void deleteAllByListingId(Long listingId)
        throws ProvisioningException;
}
