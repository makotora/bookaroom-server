package com.bookaroom.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = ReservationDTO.TABLE_NAME)
public class ReservationDTO implements Serializable
{
    public static final String TABLE_NAME = "RESERVATIONS";

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LISTING_ID")
    private Long listingId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CHECK_IN")
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Column(name = "CHECK_OUT")
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @Column(name = "GUESTS")
    private Integer numberOfGuests;

    public ReservationDTO()
    {}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getListingId()
    {
        return listingId;
    }

    public void setListingId(Long listingId)
    {
        this.listingId = listingId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
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
        return "ReservationDTO [id="
               + id
               + ", listingId="
               + listingId
               + ", userId="
               + userId
               + ", checkIn="
               + checkIn
               + ", checkOut="
               + checkOut
               + ", numberOfGuests="
               + numberOfGuests
               + "]";
    }


}
