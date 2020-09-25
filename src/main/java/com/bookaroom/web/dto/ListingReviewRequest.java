package com.bookaroom.web.dto;

public class ListingReviewRequest
{
    private Long listingId;
    private Float rating;
    private String comments;

    public ListingReviewRequest(Long listingId, Float rating, String comments)
    {
        super();
        this.listingId = listingId;
        this.rating = rating;
        this.comments = comments;
    }

    public ListingReviewRequest()
    {}

    public Long getListingId()
    {
        return listingId;
    }

    public void setListingId(Long listingId)
    {
        this.listingId = listingId;
    }

    public Float getRating()
    {
        return rating;
    }

    public void setRating(Float rating)
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
        return "ListingReviewRequest [listingId="
               + listingId
               + ", rating="
               + rating
               + ", comments="
               + comments
               + "]";
    }

}
