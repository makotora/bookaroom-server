package com.bookaroom.web.dto;

public class ListingShortViewResponse
{
    private Long id;

    private String address;

    private Double cost;

    private String currency;

    private String mainImagePath;

    private Double averageRating;

    public ListingShortViewResponse()
    {}

    public ListingShortViewResponse(
        Long id,
        String address,
        Double cost,
        String currency,
        String mainImagePath,
        Double averageRating)
    {
        super();
        this.id = id;
        this.address = address;
        this.cost = cost;
        this.currency = currency;
        this.mainImagePath = mainImagePath;
        this.averageRating = averageRating;
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

    public Double getCost()
    {
        return cost;
    }

    public void setCost(Double cost)
    {
        this.cost = cost;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getMainImagePath()
    {
        return mainImagePath;
    }

    public void setMainImagePath(String mainImagePath)
    {
        this.mainImagePath = mainImagePath;
    }

    public Double getAverageRating()
    {
        return averageRating;
    }

    public void setAverageRating(Double averageRating)
    {
        this.averageRating = averageRating;
    }

    @Override
    public String toString()
    {
        return "ListingShortViewResponse [id="
               + id
               + ", address="
               + address
               + ", cost="
               + cost
               + ", currency="
               + currency
               + ", mainImagePath="
               + mainImagePath
               + ", averageRating="
               + averageRating
               + "]";
    }


}
