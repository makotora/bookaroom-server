package com.bookaroom.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Searches")
public class SearchDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "listing_id")
    @Id
    private Long listingId;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "bedroooms")
    private Integer bedrooms;

    @Column(name = "beds")
    private Integer beds;

    @Column(name = "price")
    private String price;

    @Column(name = "user")
    private String user;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "BEGIN_DATE", columnDefinition = "DATE")
    private Date beginDate;

    @Column(name = "END_DATE", columnDefinition = "DATE")
    private Date endDate;

    public Integer getBathrooms()
    {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms)
    {
        this.bathrooms = bathrooms;
    }

    public Integer getBedrooms()
    {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms)
    {
        this.bedrooms = bedrooms;
    }

    public Integer getBeds()
    {
        return beds;
    }

    public void setBeds(Integer beds)
    {
        this.beds = beds;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
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

    public Date getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

}
