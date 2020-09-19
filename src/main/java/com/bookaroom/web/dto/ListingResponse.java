package com.bookaroom.web.dto;

import java.util.List;

public class ListingResponse
{
    private Long id;

    private String address;

    private Double longitude;

    private Double latitude;

    private Integer maxGuests;

    private Double minPrice;

    private Double costPerExtraGuest;

    private String typeStr;

    private String rules;

    private String description;

    private Integer numberOfBeds;

    private Integer numberOfBathrooms;

    private Integer numberOfBedrooms;

    private Integer area;

    private boolean hasLivingRoom;

    private List<AvailabilityRange> availabilityRanges;

    private String mainPicturePath;

    private List<String> additionalPicturePaths;

    public ListingResponse(
        Long id,
        String address,
        Double longitude,
        Double latitude,
        Integer maxGuests,
        Double minPrice,
        Double costPerExtraGuest,
        String typeStr,
        String rules,
        String description,
        Integer numberOfBeds,
        Integer numberOfBathrooms,
        Integer numberOfBedrooms,
        Integer area,
        boolean hasLivingRoom,
        List<AvailabilityRange> availabilityRanges,
        String mainPicturePath,
        List<String> additionalPicturePaths)
    {
        super();
        this.id = id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.maxGuests = maxGuests;
        this.minPrice = minPrice;
        this.costPerExtraGuest = costPerExtraGuest;
        this.typeStr = typeStr;
        this.rules = rules;
        this.description = description;
        this.numberOfBeds = numberOfBeds;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfBedrooms = numberOfBedrooms;
        this.area = area;
        this.hasLivingRoom = hasLivingRoom;
        this.availabilityRanges = availabilityRanges;
        this.mainPicturePath = mainPicturePath;
        this.additionalPicturePaths = additionalPicturePaths;
    }

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

    public String getTypeStr()
    {
        return typeStr;
    }

    public void setTypeStr(String typeStr)
    {
        this.typeStr = typeStr;
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

    public List<AvailabilityRange> getAvailabilityRanges()
    {
        return availabilityRanges;
    }

    public void setAvailabilityRanges(List<AvailabilityRange> availabilityRanges)
    {
        this.availabilityRanges = availabilityRanges;
    }

    public String getMainPicturePath()
    {
        return mainPicturePath;
    }

    public void setMainPicturePath(String mainPicturePath)
    {
        this.mainPicturePath = mainPicturePath;
    }

    public List<String> getAdditionalPicturePaths()
    {
        return additionalPicturePaths;
    }

    public void setAdditionalPicturePaths(List<String> additionalPicturePaths)
    {
        this.additionalPicturePaths = additionalPicturePaths;
    }

    @Override
    public String toString()
    {
        return "ListingResponse [id="
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
               + ", typeStr="
               + typeStr
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
               + ", availabilityRanges="
               + availabilityRanges
               + ", mainPicturePath="
               + mainPicturePath
               + ", additionalPicturePaths="
               + additionalPicturePaths
               + "]";
    }
}
