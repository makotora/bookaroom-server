package com.bookaroom.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.FileUploadDTO;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.repositories.FileUploadDAO;
import com.bookaroom.services.FileUploadService;
import com.bookaroom.util.Constants;

@Service
public class FileUploadServiceImpl implements FileUploadService
{

    @Autowired
    private FileUploadDAO fileUploadDAO;

    @Override
    @Transactional
    public FileUploadDTO uploadFile(MultipartFile file, String serverDirectoryPath, String fileName)
        throws ProvisioningException
    {
        createDirectoryIfDoesNotExist(serverDirectoryPath);
        String filePath = buildFilePath(serverDirectoryPath, fileName, getFileExtension(file));
        saveFile(file, filePath);

        FileUploadDTO fileUpload = new FileUploadDTO();
        fileUpload.setServerPath(filePath);
        fileUpload.setOriginalName(file.getOriginalFilename());
        fileUploadDAO.save(fileUpload);

        return fileUpload;
    }

    private void createDirectoryIfDoesNotExist(String serverDirectoryPath)
    {
        File directory = new File(serverDirectoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private String buildFilePath(String serverDirectoryPath, String fileName, String fileExtension)
    {
        return new File(serverDirectoryPath, fileName).getPath() + "." + fileExtension;
    }

    private String getFileExtension(MultipartFile file)
    {
        String originalExtension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (originalExtension == null || originalExtension.trim().isEmpty()) {
            return Constants.DEFAULT_PICTURE_EXTENSION;
        }

        return originalExtension;
    }

    private void saveFile(MultipartFile file, String filePath)
        throws ProvisioningException
    {
        Path path = Paths.get(filePath);
        try {
            Files.write(path, file.getBytes());
        }
        catch (IOException e) {
            throw new ProvisioningException("Error saving file");
        }
    }

    @Override
    @Transactional
    public void deleteFile(Long id)
        throws ProvisioningException
    {
        FileUploadDTO fileUpload = fileUploadDAO.findOne(id);
        if (fileUpload == null) {
            throw new ProvisioningException("File does not exist");
        }

        deleteFile(fileUpload.getServerPath());
        fileUploadDAO.delete(fileUpload);
    }

    private void deleteFile(String filePath)
        throws ProvisioningException
    {
        Path path = Paths.get(filePath);
        try {
            Files.delete(path);
        }
        catch (IOException e) {
            throw new ProvisioningException("Error deleting file");
        }
    }

    @Override
    public FileUploadDTO findUserPictureFile(Long userId)
    {
        return fileUploadDAO.findUserPictureFile(userId);
    }

    @Override
    public List<FileUploadDTO> findListingPictureFiles(Long listingId)
    {
        return fileUploadDAO.findListingPictureFiles(listingId);
    }

    @Override
    public FileUploadDTO findById(Long id)
    {
        return fileUploadDAO.findOne(id);
    }

}
