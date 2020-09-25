package com.bookaroom.services;

import java.security.Principal;
import java.util.List;

import com.bookaroom.entities.ListingReviewDTO;
import com.bookaroom.exceptions.AlreadyReviewedException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.web.dto.ListingReviewResponse;

public interface ListingReviewService
{
    public ListingReviewDTO addReview(Principal principal, Long listingId, Float rating, String comments)
        throws AlreadyReviewedException, UserNotFoundException, UserNotAuthenticatedException;

    public ListingReviewDTO addReview(Long listingId, Long userId, Float rating, String comments)
        throws AlreadyReviewedException;

    public ListingReviewDTO findByListingAndUser(Long listingId, Long userId);

    public List<ListingReviewResponse> viewByHostUserId(Long hostUserId);

    public List<ListingReviewDTO> findByHostUserId(Long hostUserId);
}
