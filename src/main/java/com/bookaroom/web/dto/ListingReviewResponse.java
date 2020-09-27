package com.bookaroom.web.dto;

public class ListingReviewResponse
{
    private Long userId;
    private String userImagePath;
    private String userName;
    private Float rating;
    private String comments;

    public ListingReviewResponse(
        Long userId,
        String userImagePath,
        String userName,
        Float rating,
        String comments)
    {
        super();
        this.userId = userId;
        this.userImagePath = userImagePath;
        this.userName = userName;
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

    public String getUserImagePath()
    {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath)
    {
        this.userImagePath = userImagePath;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
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
               + ", userImagePath="
               + userImagePath
               + ", userName="
               + userName
               + ", rating="
               + rating
               + ", comments="
               + comments
               + "]";
    }


}
