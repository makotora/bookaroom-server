package com.bookaroom.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.FileUploadDTO;
import com.bookaroom.exceptions.ProvisioningException;

public interface FileUploadService
{
    public FileUploadDTO uploadFile(MultipartFile file, String serverDirectoryPath, String fileName)
        throws ProvisioningException;

    public void deleteFile(Long id)
        throws ProvisioningException;

    public FileUploadDTO findUserPictureFile(Long userId);

    public List<FileUploadDTO> findListingPictureFiles(Long listingId);

    public FileUploadDTO findById(Long id);

}
