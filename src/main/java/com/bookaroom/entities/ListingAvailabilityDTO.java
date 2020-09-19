package com.bookaroom.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bookaroom.entities.keys.ListingAvailabilityPK;

@Entity
@Table(name = "LISTING_AVAILABILITY")
public class ListingAvailabilityDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ListingAvailabilityPK id;

    public ListingAvailabilityPK getId()
    {
        return id;
    }

    public void setId(ListingAvailabilityPK id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ListingAvailabilityDTO [id=" + id + "]";
    }
}
