package com.bookaroom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LISTING_PICTURES")
public class ListingPictureDTO
{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "LISTING_ID")
    private long listingId;

    @Column(name = "PICTURE_FILE_UPLOAD_ID")
    private long pictureFileUploadId;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getListingId()
    {
        return listingId;
    }

    public void setListingId(long listingId)
    {
        this.listingId = listingId;
    }

    public long getPictureFileUploadId()
    {
        return pictureFileUploadId;
    }

    public void setPictureFileUploadId(long pictureFileUploadId)
    {
        this.pictureFileUploadId = pictureFileUploadId;
    }

    @Override
    public String toString()
    {
        return "ListingPictureDTO [id="
               + id
               + ", listingId="
               + listingId
               + ", pictureFileUploadId="
               + pictureFileUploadId
               + "]";
    }

}
