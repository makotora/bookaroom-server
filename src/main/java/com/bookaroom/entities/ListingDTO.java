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
              + "    l.MIN_PRICE + l.EXTRA_GUEST_COST as total_cost," // 2
              + "    fu.SERVER_PATH," // 3
              + "    ("
              + "      SELECT AVG(rating)"
              + "      FROM " + ListingReviewDTO.TABLE_NAME + " lr"
              + "      WHERE lr.LISTING_ID = l.ID"
              + "    ) as average_rating" // 4
              + "    FROM " + ListingDTO.TABLE_NAME + " l"
              + "    JOIN " + FileUploadDTO.TABLE_NAME + " fu"
              + "    on fu.ID = l.MAIN_PICTURE_FILE_ID"
        // @formatter:on
    )
})
@Entity
@Table(name = ListingDTO.TABLE_NAME)
public class ListingDTO implements Serializable
{
    public static final String TABLE_NAME = "LISTINGS";

    public static final String QUERY_NAME_PREFIX = "ListingDTO.";
    public static final String QUERY_NAME_FIND_ALL_SHORT_VIEWS = QUERY_NAME_PREFIX + "findAllShortViews";

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
