package com.bookaroom.web.dto;

public class ListingReviewResponse
{
    private Long userId;
    private Float rating;
    private String comments;

    public ListingReviewResponse(Long userId, Float rating, String comments)
    {
        super();
        this.userId = userId;
        this.rating = rating;
        this.comments = comments;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
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
        return "ListingReviewResponse [userId="
               + userId
               + ", rating="
               + rating
               + ", comments="
               + comments
               + "]";
    }

}
