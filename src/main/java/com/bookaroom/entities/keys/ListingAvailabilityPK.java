package com.bookaroom.entities.keys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ListingAvailabilityPK implements Serializable
{
    private static final long serialVersionUID = 7312537526310862376L;

    @Column(name = "LISTING_ID")
    private Long listingId;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    public ListingAvailabilityPK()
    {}

    public ListingAvailabilityPK(Long listingId, Date date)
    {
        super();
        this.listingId = listingId;
        this.date = date;
    }

    public Long getListingId()
    {
        return listingId;
    }

    public void setListingId(Long listingId)
    {
        this.listingId = listingId;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((listingId == null) ? 0 : listingId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListingAvailabilityPK other = (ListingAvailabilityPK) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        }
        else if (!date.equals(other.date))
            return false;
        if (listingId == null) {
            if (other.listingId != null)
                return false;
        }
        else if (!listingId.equals(other.listingId))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ListingAvailabilityId [listingId=" + listingId + ", date=" + date + "]";
    }
}
