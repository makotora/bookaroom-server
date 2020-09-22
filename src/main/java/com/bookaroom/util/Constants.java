package com.bookaroom.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.bookaroom.enums.UserRole;

public class Constants
{
    public static final boolean DEBUG = true;

    public static final String DYNAMIC_RESOURCES_PATH = "src/main/resources/dynamic";
    public static final String LISTING_PICTURES_DIRECTORY = DYNAMIC_RESOURCES_PATH + "/uploaded_files/";
    public static final String USER_PICTURES_DIRECTORY = DYNAMIC_RESOURCES_PATH + "/userPictures/";

    public static final String DEFAULT_PICTURE_EXTENSION = "png";

    public static final Set<UserRole> HOST_ROLE = new HashSet<UserRole>(Arrays.asList(UserRole.Host));
    public static final Set<UserRole> GUEST_ROLE = new HashSet<UserRole>(Arrays.asList(UserRole.Guest));
    public static final Set<UserRole> ALL_ROLES = new HashSet<UserRole>(Arrays.asList(UserRole.Host,
                                                                                      UserRole.Guest));

    public static final String USER_PICTURES_RESOURCE_HANDLER = "userPictures";
    public static final String LISTING_PICTURES_RESOURCE_HANDLER_PATH = "listingPictures";

    public static final String DEFAULT_CURRENCY = "\u20ac";

    public static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";
}
