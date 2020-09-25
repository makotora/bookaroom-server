package com.bookaroom.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.bookaroom.enums.ListingType;

@NamedNativeQueries({
    @NamedNativeQuery(
        name = ListingDTO.QUERY_NAME_FIND_ALL_SHORT_VIEWS,
        // @formatter:off
        query = "  SELECT"
              + "    l.ID," // 0
              + "    l.ADDRESS," // 1
              + "    l.MIN_PRICE + l.EXTRA_GUEST_COST as TOTAL_COST," // 2
              + "    fu.SERVER_PATH," // 3
              + "    ("
              + "      SELECT AVG(rating)"
              + "      FROM " + ListingReviewDTO.TABLE_NAME + " lr"
              + "      WHERE lr.LISTING_ID = l.ID"
              + "    ) as AVERAGE_RATING" // 4
              + "    FROM " + ListingDTO.TABLE_NAME + " l"
              + "    JOIN " + FileUploadDTO.TABLE_NAME + " fu"
              + "    on fu.ID = l.MAIN_PICTURE_FILE_ID"
        // @formatter:on
    ),
    @NamedNativeQuery(
        name = ListingDTO.QUERY_NAME_FIND_USER_RECOMMENDED_SHORT_VIEWS,
        // @formatter:off
        query = "  SELECT"
              + "    x.LISTING_ID," // 0
              + "    x.ADDRESS," // 1
              + "    x.TOTAL_COST," // 2
              + "    x.MAIN_PIC_PATH," // 3
              + "    x.AVERAGE_RATING" // 4
              + "  FROM ("
              + "      SELECT"
              + "        l.ID as LISTING_ID,"
              + "        l.ADDRESS as ADDRESS,"
              + "        l.MIN_PRICE + l.EXTRA_GUEST_COST as TOTAL_COST,"
              + "        fu.SERVER_PATH as MAIN_PIC_PATH,"
              + "        ("
              + "          SELECT AVG(rating)"
              + "          FROM " + ListingReviewDTO.TABLE_NAME + " lr"
              + "          WHERE lr.LISTING_ID = l.ID"
              + "        ) as AVERAGE_RATING"
              + "      FROM " + ListingDTO.TABLE_NAME + " l"
              + "      JOIN " + FileUploadDTO.TABLE_NAME + " fu"
              + "        on fu.ID = l.MAIN_PICTURE_FILE_ID"
              + "      WHERE EXISTS ("
              + "        SELECT 1"
              + "        FROM " + SearchDTO.TABLE_NAME + " s"
              + "        WHERE s.user_id = ?" + ListingDTO.QUERY_PARAM_RECOMM_INDEX_USER_ID
              + "        AND datediff(NOW(), s.search_date) <= ?" + ListingDTO.QUERY_PARAM_RECOMM_INDEX_MAX_DAYS_AFTER_SEARCH
              + "        AND l.address like CONCAT('%', s.state, '%')"
              + "        AND l.address like CONCAT('%', s.city, '%')"
              + "        AND l.address like CONCAT('%', s.country, '%')"
              + "      )"
              + "  ) x"
              + "  ORDER BY x.AVERAGE_RATING LIMIT ?" + ListingDTO.QUERY_PARAM_RECOMM_INDEX_MAX_RESULTS
         // @formatter:on
    ),
    @NamedNativeQuery(
        name = ListingDTO.QUERY_NAME_SEARCH_SHORT_VIEWS,
        // @formatter:off
        query = "  SELECT" 
              + "    x.LISTING_ID,"
              + "    x.ADDRESS,"
              + "    x.TOTAL_COST,"
              + "    x.MAIN_PIC_PATH,"
              + "    x.AVERAGE_RATING"
              + "  FROM ("
              + "      SELECT"
              + "        l.ID as LISTING_ID,"
              + "        l.ADDRESS as ADDRESS ,"
              + "        l.MIN_PRICE + (l.EXTRA_GUEST_COST * ?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_NUM_OF_GUESTS + ") as TOTAL_COST,"
              + "        fu.SERVER_PATH as MAIN_PIC_PATH,"
              + "        ("
              + "          SELECT AVG(rating)"
              + "          FROM " + ListingReviewDTO.TABLE_NAME + " lr"
              + "          WHERE lr.LISTING_ID = l.ID"
              + "        ) as AVERAGE_RATING"
              + "      FROM " + ListingDTO.TABLE_NAME + " l"
              + "      JOIN " + FileUploadDTO.TABLE_NAME + " fu"
              + "        on fu.ID = l.MAIN_PICTURE_FILE_ID"
              + "      WHERE"
              + "        ?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_NUM_OF_GUESTS + " <= l.MAX_GUESTS"
              + "        AND l.address like CONCAT('%', ?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_STATE + ", '%')"
              + "        AND l.address like CONCAT('%', ?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_CITY + ", '%')"
              + "        AND l.address like CONCAT('%', ?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_COUNTRY + ", '%')"
              + "        AND ("
              + "          SELECT COUNT(*)"
              + "          FROM " + ListingAvailabilityDTO.TABLE_NAME + " la"
              + "          WHERE la.LISTING_ID = l.ID"
              + "          AND la.date >= ?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_CHECK_IN
              + "          AND la.date < ?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_CHECK_OUT
              + "        ) = DATEDIFF(?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_CHECK_OUT + ", ?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_CHECK_IN + ")"
              + "        AND NOT EXISTS ("
              + "          SELECT 1"
              + "          FROM " + ReservationDTO.TABLE_NAME + " r"
              + "          WHERE"
              + "            r.LISTING_ID = l.ID"
              + "            AND ?" +ListingDTO.QUERY_PARAM_SEARCH_INDEX_CHECK_IN + " < r.CHECK_OUT"
              + "            AND r.CHECK_IN < ?" + ListingDTO.QUERY_PARAM_SEARCH_INDEX_CHECK_OUT
              + "        )"
              + "  ) x"
              + "  ORDER BY x.TOTAL_COST ASC"),
        // @formatter:on
    @NamedNativeQuery(name = ListingDTO.QUERY_NAME_GET_IF_IS_AVAILABLE,
    // @formatter:off
        query = "  SELECT"
              + "    l.ID as LISTING_ID"
              + "  FROM "
              + ListingDTO.TABLE_NAME
              + " l"
              + "  WHERE l.ID = ?"
              + ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_LISTING_ID
              + "  AND ("
              + "    SELECT COUNT(*)"
              + "    FROM "
              + ListingAvailabilityDTO.TABLE_NAME
              + "    la"
              + "    WHERE la.LISTING_ID = l.ID"
              + "    AND la.date >= ?"
              + ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_IN
              + "    AND la.date < ?"
              + ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_OUT
              + "  ) = DATEDIFF(?"
              + ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_OUT
              + ", ?"
              + ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_IN
              + ")"
              + "  AND NOT EXISTS ("
              + "    SELECT 1"
              + "    FROM "
              + ReservationDTO.TABLE_NAME
              + "    r"
              + "    WHERE"
              + "    r.LISTING_ID = l.ID"
              + "    AND ?"
              + ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_IN
              + "    < r.CHECK_OUT"
              + "    AND r.CHECK_IN < ?"
              + ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_OUT
              + "  )")
        // @formatter:on

})
@Entity
@Table(name = ListingDTO.TABLE_NAME)
public class ListingDTO implements Serializable
{
    public static final String TABLE_NAME = "LISTINGS";

    public static final String QUERY_NAME_PREFIX = "ListingDTO.";
    public static final String QUERY_NAME_FIND_ALL_SHORT_VIEWS = QUERY_NAME_PREFIX + "findAllShortViews";
    public static final String QUERY_NAME_FIND_USER_RECOMMENDED_SHORT_VIEWS = QUERY_NAME_PREFIX
                                                                              + "findUserRecommendedShortViews";
    public static final String QUERY_NAME_SEARCH_SHORT_VIEWS = QUERY_NAME_PREFIX + "searchShortViews";
    public static final String QUERY_NAME_GET_IF_IS_AVAILABLE = QUERY_NAME_PREFIX + "getIfIsAvailable";

    public static final int QUERY_PARAM_RECOMM_INDEX_USER_ID = 1;
    public static final int QUERY_PARAM_RECOMM_INDEX_MAX_DAYS_AFTER_SEARCH = 2;
    public static final int QUERY_PARAM_RECOMM_INDEX_MAX_RESULTS = 3;

    public static final int QUERY_PARAM_SEARCH_INDEX_STATE = 1;
    public static final int QUERY_PARAM_SEARCH_INDEX_CITY = 2;
    public static final int QUERY_PARAM_SEARCH_INDEX_COUNTRY = 3;
    public static final int QUERY_PARAM_SEARCH_INDEX_CHECK_IN = 4;
    public static final int QUERY_PARAM_SEARCH_INDEX_CHECK_OUT = 5;
    public static final int QUERY_PARAM_SEARCH_INDEX_NUM_OF_GUESTS = 6;

    public static final int QUERY_PARAM_IS_AVAILABLE_INDEX_LISTING_ID = 1;
    public static final int QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_IN = 2;
    public static final int QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_OUT = 3;

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "LONGITUDE", precision = 10, scale = 2)
    private Double longitude;

    @Column(name = "LATITUDE", precision = 10, scale = 2)
    private Double latitude;

    @Column(name = "MAX_GUESTS")
    private Integer maxGuests;

    @Column(name = "MIN_PRICE")
    private Double minPrice;

    @Column(name = "EXTRA_GUEST_COST")
    private Double costPerExtraGuest;

    @Column(name = "TYPE")
    @Enumerated(EnumType.ORDINAL)
    private ListingType type;

    @Column(name = "RULES")
    private String rules;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "NUM_BEDS")
    private Integer numberOfBeds;

    @Column(name = "NUM_BATHROOMS")
    private Integer numberOfBathrooms;

    @Column(name = "NUM_BEDROOMS")
    private Integer numberOfBedrooms;

    @Column(name = "AREA")
    private Integer area;

    @Column(name = "HAS_LIVING_ROOM")
    private boolean hasLivingRoom;

    @Column(name = "MAIN_PICTURE_FILE_ID")
    private Long mainPictureFileUploadId;

    public ListingDTO()
    {}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Integer getMaxGuests()
    {
        return maxGuests;
    }

    public void setMaxGuests(Integer maxGuests)
    {
        this.maxGuests = maxGuests;
    }

    public Double getMinPrice()
    {
        return minPrice;
    }

    public void setMinPrice(Double minPrice)
    {
        this.minPrice = minPrice;
    }

    public Double getCostPerExtraGuest()
    {
        return costPerExtraGuest;
    }

    public void setCostPerExtraGuest(Double costPerExtraGuest)
    {
        this.costPerExtraGuest = costPerExtraGuest;
    }

    public ListingType getType()
    {
        return type;
    }

    public void setType(ListingType type)
    {
        this.type = type;
    }

    public String getRules()
    {
        return rules;
    }

    public void setRules(String rules)
    {
        this.rules = rules;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getNumberOfBeds()
    {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds)
    {
        this.numberOfBeds = numberOfBeds;
    }

    public Integer getNumberOfBathrooms()
    {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(Integer numberOfBathrooms)
    {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public Integer getNumberOfBedrooms()
    {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(Integer numberOfBedrooms)
    {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public Integer getArea()
    {
        return area;
    }

    public void setArea(Integer area)
    {
        this.area = area;
    }

    public boolean isHasLivingRoom()
    {
        return hasLivingRoom;
    }

    public void setHasLivingRoom(boolean hasLivingRoom)
    {
        this.hasLivingRoom = hasLivingRoom;
    }

    public Long getMainPictureFileUploadId()
    {
        return mainPictureFileUploadId;
    }

    public void setMainPictureFileUploadId(Long mainPictureFileUploadId)
    {
        this.mainPictureFileUploadId = mainPictureFileUploadId;
    }

    @Override
    public String toString()
    {
        return "ListingDTO [id="
               + id
               + ", address="
               + address
               + ", longitude="
               + longitude
               + ", latitude="
               + latitude
               + ", maxGuests="
               + maxGuests
               + ", minPrice="
               + minPrice
               + ", costPerExtraGuest="
               + costPerExtraGuest
               + ", type="
               + type
               + ", rules="
               + rules
               + ", description="
               + description
               + ", numberOfBeds="
               + numberOfBeds
               + ", numberOfBathrooms="
               + numberOfBathrooms
               + ", numberOfBedrooms="
               + numberOfBedrooms
               + ", area="
               + area
               + ", hasLivingRoom="
               + hasLivingRoom
               + ", mainPictureFileUploadId="
               + mainPictureFileUploadId
               + "]";
    }
}
