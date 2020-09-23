package com.bookaroom.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.UserDTO;
import com.bookaroom.enums.ListingType;
import com.bookaroom.enums.UserRole;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.services.ListingService;
import com.bookaroom.services.TestService;
import com.bookaroom.services.UserService;
import com.bookaroom.util.Utils;
import com.bookaroom.web.dto.AvailabilityRange;

@Service
public class TestServiceImpl implements TestService
{
    @Autowired
    private UserService users;

    @Autowired
    private ListingService listings;

    @Override
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
        throws ProvisioningException
    {
        for (int i = userStartOffset; i < userStartOffset + numberOfData; i++) {
            UserDTO newUser = users.create("test_user_" + (i + 1),
                                                 "1234",
                                                 "test",
                                                 "user",
                                                 "test@user.com",
                                                 "1234",
                                                 UserRole.Host,
                                                 mainPictureFile);

            List<AvailabilityRange> newListingAvailabilities = new ArrayList<AvailabilityRange>(1);
            Random random = new Random();
            Date startDate = new Date();
            Date endDate = Utils.addDays(startDate, random.nextInt(90));
            newListingAvailabilities.add(new AvailabilityRange(startDate, endDate));

            listings.create(newUser.getId(),
                            address,
                            longitude,
                            latitude,
                            random.nextInt(10),
                            random.nextDouble() * 1000,
                            random.nextDouble() * 20,
                            random.nextBoolean() ? ListingType.ROOM : ListingType.HOUSE,
                            rules,
                            description,
                            numberOfBeds,
                            numberOfBathrooms,
                            numberOfBedrooms,
                            area,
                            random.nextBoolean(),
                            newListingAvailabilities,
                            mainPictureFile,
                            listingPictureFiles);
        }
    }

}
