package com.bookaroom.services.impl;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookaroom.entities.ListingReviewDTO;
import com.bookaroom.entities.UserDTO;
import com.bookaroom.exceptions.AlreadyReviewedException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.repositories.ListingReviewDAO;
import com.bookaroom.services.ListingReviewService;
import com.bookaroom.services.UserService;
import com.bookaroom.util.Utils;
import com.bookaroom.web.dto.ListingReviewResponse;

@Service
public class ListingReviewServiceImpl implements ListingReviewService
{
    @Autowired
    private ListingReviewDAO listingReviewDAO;

    @Autowired
    private UserService users;

    @Override
    @Transactional
    public ListingReviewDTO addReview(Principal principal, Long listingId, Float rating, String comments)
        throws AlreadyReviewedException, UserNotFoundException, UserNotAuthenticatedException
    {
        UserDTO user = users.findByPrincipal(principal);

        return addReview(listingId, user.getId(), rating, comments);
    }

    @Override
    @Transactional
    public ListingReviewDTO addReview(Long listingId, Long userId, Float rating, String comments)
        throws AlreadyReviewedException
    {
        if (findByListingAndUser(listingId, userId) != null) {
            throw new AlreadyReviewedException();
        }

        ListingReviewDTO review = new ListingReviewDTO();
        review.setListingId(listingId);
        review.setUserId(userId);
        review.setRating(rating);
        review.setComments(comments);

        listingReviewDAO.save(review);

        return review;
    }

    @Override
    public ListingReviewDTO findByListingAndUser(Long listingId, Long userId)
    {
        List<ListingReviewDTO> listingUserReviews = listingReviewDAO.findByListingIdAndUserId(listingId,
                                                                                              userId);

        return listingUserReviews != null && !listingUserReviews.isEmpty() ? listingUserReviews.get(0) : null;
    }

    @Override
    public List<ListingReviewResponse> viewByHostUserId(Long hostUserId)
    {
        return listingReviewDAO.findHostReviews(hostUserId)
                               .stream()
                               .map(cols -> new ListingReviewResponse(((BigInteger) cols[0]).longValue(),
                                                                      (cols[1] != null ? Utils.prepareUserPicturePath((String) cols[1])
                                                                                       : null),
                                                                      (String) cols[2],
                                                                      (Float) cols[3],
                                                                      (String) cols[4]))
                                           .collect(Collectors.toList());
    }

    @Override
    public List<ListingReviewDTO> findByHostUserId(Long hostUserId)
    {
        return listingReviewDAO.findByHostUserId(hostUserId);
    }

}
