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
@Table(name = SearchDTO.TABLE_NAME)
public class SearchDTO implements Serializable
{
    public static final String TABLE_NAME = "SEARCHES";

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "STATE")
    private String state;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CHECK_IN")
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Column(name = "CHECK_OUT")
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @Column(name = "NUM_OF_GUESTS")
    private Integer numberOfGuests;

    @Column(name = "SEARCH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date searchDate;

    public SearchDTO()
    {}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
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

    public Date getSearchDate()
    {
        return searchDate;
    }

    public void setSearchDate(Date searchDate)
    {
        this.searchDate = searchDate;
    }

    @Override
    public String toString()
    {
        return "SearchDTO [id="
               + id
               + ", userId="
               + userId
               + ", state="
               + state
               + ", city="
               + city
               + ", country="
               + country
               + ", checkIn="
               + checkIn
               + ", checkOut="
               + checkOut
               + ", numberOfGuests="
               + numberOfGuests
               + ", searchDate="
               + searchDate
               + "]";
    }



}