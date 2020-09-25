package com.bookaroom.web.dto;

import java.util.Date;

import com.bookaroom.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ReservationRequest
{
    private Long listingId;

    @JsonSerialize(as = Date.class)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = Constants.DEFAULT_TIMEZONE)
    private Date checkIn;

    @JsonSerialize(as = Date.class)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = Constants.DEFAULT_TIMEZONE)
    private Date checkOut;

    private Integer numberOfGuests;

    public ReservationRequest()
    {
        super();
    }

    public ReservationRequest(Long listingId, Date checkIn, Date checkOut, Integer numberOfGuests)
    {
        super();
        this.listingId = listingId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfGuests = numberOfGuests;
    }

    public Long getListingId()
    {
        return listingId;
    }

    public void setListingId(Long listingId)
    {
        this.listingId = listingId;
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
        return "ReservationRequest [listingId="
               + listingId
               + ", checkIn="
               + checkIn
               + ", checkOut="
               + checkOut
               + ", numberOfGuests="
               + numberOfGuests
               + "]";
    }

}
