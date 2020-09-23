package com.bookaroom.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.exceptions.ProvisioningException;

public interface TestService
{
    public void createTestData(
        int userStartOffset,
        int numberOfData,
        String address,
        Double longitude,
        Double latitude,
        String rules,
        String description,
        Integer numberOfBeds,
        Integer numberOfBathrooms,
        Integer numberOfBedrooms,
        Integer area,
        MultipartFile mainPictureFile,
        List<MultipartFile> listingPictureFiles)
        throws ProvisioningException;
}
