package com.bookaroom.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.FileUploadDTO;
import com.bookaroom.entities.ListingPictureDTO;
import com.bookaroom.exceptions.EntityNotFoundException;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.repositories.ListingPictureDAO;
import com.bookaroom.services.FileUploadService;
import com.bookaroom.services.ListingPictureService;
import com.bookaroom.util.Constants;

@Service
public class ListingPictureServiceImpl implements ListingPictureService
{

    @Autowired
    ListingPictureDAO listingPictureDAO;

    @Autowired
    FileUploadService fileUploads;

    @Override
    @Transactional
    public ListingPictureDTO add(Long listingId, MultipartFile file)
        throws ProvisioningException
    {
        String newPictureName = generateNewPictureFileName(listingId);
        FileUploadDTO fileUpload = fileUploads.uploadFile(file,
                                                          Constants.LISTING_PICTURES_DIRECTORY,
                                                          newPictureName);

        ListingPictureDTO listingPicture = new ListingPictureDTO();
        listingPicture.setListingId(listingId);
        listingPicture.setPictureFileUploadId(fileUpload.getId());

        listingPictureDAO.save(listingPicture);//save file to give it an id

        return listingPicture;
    }

    private String generateNewPictureFileName(Long listingId)
    {
        Long picturesCount = listingPictureDAO.countByListingId(listingId);
        Long newPictureCount = picturesCount + 1;

        return listingId.toString() + "_" + newPictureCount.toString();
    }

    @Override
    public void delete(Long id)
        throws ProvisioningException
    {
        ListingPictureDTO listingPicture = listingPictureDAO.findById(id);

        if (listingPicture == null) {
            throw new ProvisioningException("Picture does not exist");
        }

        fileUploads.deleteFile(listingPicture.getPictureFileUploadId());

        listingPictureDAO.delete(listingPicture);
    }

    @Override
    public List<ListingPictureDTO> findByListingId(Long listingId)
    {
        return listingPictureDAO.findByListingId(listingId);
    }

    @Override
    public ListingPictureDTO findById(long id)
        throws EntityNotFoundException
    {
        return listingPictureDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteAllByListingId(Long listingId)
        throws ProvisioningException
    {
        for (ListingPictureDTO listingPicture : findByListingId(listingId)) {
            delete(listingPicture.getId());
        }
    }

}
