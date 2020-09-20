package com.bookaroom.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name = "BEGIN_DATE", columnDefinition = "DATE")
    private Date beginDate;

    @Column(name = "END_DATE", columnDefinition = "DATE")
    private Date endDate;

    public long getListingId()
    {
        return listingId;
    }

    public void setListingId(long listingId)
    {
        this.listingId = listingId;
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
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

    public static long getSerialversionuid()
    {
        return serialVersionUID;
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
               + ", beginDate="
               + beginDate
               + ", endDate="
               + endDate
               + "]";
    }

}
