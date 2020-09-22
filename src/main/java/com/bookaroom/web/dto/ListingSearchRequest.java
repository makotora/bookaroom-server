package com.bookaroom.web.dto;

import java.util.Date;

public class ListingSearchRequest
{
    private String state;
    private String city;
    private String country;
    private Date checkIn;
    private Date checkOut;
    private Integer numberOfGuests;

    public ListingSearchRequest(
        String state,
        String city,
        String country,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
    {
        this.state = state;
        this.city = city;
        this.country = country;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfGuests = numberOfGuests;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public Date getCheckIn()
    {
        return checkIn;
    }

    public void setCheckIn(Date checkIn)
    {
        this.checkIn = checkIn;
    }

    public Date getCheckOut()
    {
        return checkOut;
    }

    public void setCheckOut(Date checkOut)
    {
        this.checkOut = checkOut;
    }

    public Integer getNumberOfGuests()
    {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests)
    {
        this.numberOfGuests = numberOfGuests;
    }

    @Override
    public String toString()
    {
        return "ListingSearchRequest{"
               + "state='"
               + state
               + '\''
               + ", city='"
               + city
               + '\''
               + ", country='"
               + country
               + '\''
               + ", checkIn="
               + checkIn
               + ", checkOut="
               + checkOut
               + ", numberOfGuests="
               + numberOfGuests
               + '}';
    }
}
