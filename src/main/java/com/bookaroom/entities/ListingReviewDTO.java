package com.bookaroom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQueries({
    @NamedNativeQuery(
        name = ListingReviewDTO.QUERY_NAME_FIND_HOST_REVIEWS,
        query = "  SELECT"
                + "      ru.id,"
                + "      furu.server_path,"
                + "      ru.name,"
                + "      r.rating,"
                + "      r.comments"
                + "    FROM "
                + UserDTO.TABLE_NAME
                + " u"
                + "    JOIN "
                + ListingReviewDTO.TABLE_NAME
                + " r on r.listing_id = u.listing_id"
                + "    JOIN "
                + UserDTO.TABLE_NAME
                + " ru on ru.id = r.user_id"
                + "    LEFT JOIN "
                + FileUploadDTO.TABLE_NAME
                + " furu on furu.id = ru.PICTURE_FILE_UPLOAD_ID"
                + "    WHERE u.id = ?1")})
@Entity
@Table(name = ListingReviewDTO.TABLE_NAME)
public class ListingReviewDTO
{
    public static final String TABLE_NAME = "LISTING_REVIEWS";

    public static final String QUERY_NAME_PREFIX = "ListingReviewDTO.";
    public static final String QUERY_NAME_FIND_HOST_REVIEWS = QUERY_NAME_PREFIX + "findHostReviews";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LISTING_ID")
    private Long listingId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "RATING")
    private Float rating;

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
