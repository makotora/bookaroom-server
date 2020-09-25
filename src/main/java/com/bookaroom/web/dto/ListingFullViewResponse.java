package com.bookaroom.web.dto;

import java.util.List;

public class ListingFullViewResponse
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

    private Double totalCost;

    private String currency;

    private String mainPicturePath;

    private List<String> additionalPicturePaths;

    private Long hostId;

    private String hostPicturePath;

    private Boolean isAvailable;

    private Boolean hasLastReservationPassed;

    public ListingFullViewResponse(
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
        Double totalCost,
        String currency,
        String mainPicturePath,
        List<String> additionalPicturePaths,
        Long hostId,
        String hostPicturePath,
        Boolean isAvailable,
        Boolean hasLastReservationPassed)
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
        this.totalCost = totalCost;
        this.currency = currency;
        this.mainPicturePath = mainPicturePath;
        this.additionalPicturePaths = additionalPicturePaths;
        this.hostId = hostId;
        this.hostPicturePath = hostPicturePath;
        this.isAvailable = isAvailable;
        this.hasLastReservationPassed = hasLastReservationPassed;
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

    public Double getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
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

    public Long getHostId()
    {
        return hostId;
    }

    public void setHostId(Long hostId)
    {
        this.hostId = hostId;
    }

    public String getHostPicturePath()
    {
        return hostPicturePath;
    }

    public void setHostPicturePath(String hostPicturePath)
    {
        this.hostPicturePath = hostPicturePath;
    }

    public Boolean getIsAvailable()
    {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable)
    {
        this.isAvailable = isAvailable;
    }

    public Boolean getHasLastReservationPassed()
    {
        return hasLastReservationPassed;
    }

    public void setHasLastReservationPassed(Boolean hasLastReservationPassed)
    {
        this.hasLastReservationPassed = hasLastReservationPassed;
    }

    @Override
    public String toString()
    {
        return "ListingFullViewResponse [id="
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
               + ", totalCost="
               + totalCost
               + ", currency="
               + currency
               + ", mainPicturePath="
               + mainPicturePath
               + ", additionalPicturePaths="
               + additionalPicturePaths
               + ", hostId="
               + hostId
               + ", hostPicturePath="
               + hostPicturePath
               + ", isAvailable="
               + isAvailable
               + ", hasLastReservationPassed="
               + hasLastReservationPassed
               + "]";
    }


}
