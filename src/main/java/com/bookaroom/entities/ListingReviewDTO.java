package com.bookaroom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = ListingReviewDTO.TABLE_NAME)
public class ListingReviewDTO
{
    public static final String TABLE_NAME = "LISTING_REVIEWS";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LISTING_ID")
    private Long listingId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "COMMENTS")
    private String comments;

    public ListingReviewDTO()
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

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    @Override
    public String toString()
    {
        return "ListingReviewDTO [id="
               + id
               + ", listingId="
               + listingId
               + ", userId="
               + userId
               + ", rating="
               + rating
               + ", comments="
               + comments
               + "]";
    }

}
