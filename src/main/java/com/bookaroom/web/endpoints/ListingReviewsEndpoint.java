package com.bookaroom.web.endpoints;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookaroom.exceptions.AlreadyReviewedException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.services.ListingReviewService;
import com.bookaroom.web.dto.ActionResponse;
import com.bookaroom.web.dto.ListingReviewRequest;
import com.bookaroom.web.dto.ListingReviewResponse;

@RestController
@RequestMapping("reviews")
public class ListingReviewsEndpoint
{

    @Autowired
    private ListingReviewService listingReviews;

    @RequestMapping(
        value = "/addReview",
        method = RequestMethod.POST)
    public ActionResponse addReview(
        Principal principal,
        @RequestBody ListingReviewRequest listingReviewRequest)
    {
        try {
            listingReviews.addReview(principal,
                                     listingReviewRequest.getListingId(),
                                     listingReviewRequest.getRating(),
                                     listingReviewRequest.getComments());
        }
        catch (AlreadyReviewedException e) {
            return new ActionResponse(false, "You have already reviewed this listing");
        }
        catch (UserNotFoundException e) {
            return new ActionResponse(false, "Invalid user");
        }
        catch (UserNotAuthenticatedException e) {
            return new ActionResponse(false, "Please login again. Authentication token expired");
        }

        return new ActionResponse(true, "Review was successfully submitted");
    }

    @RequestMapping(value = "/getByHostUserId", method = RequestMethod.GET)
    public List<ListingReviewResponse> getByHostUserId(@RequestParam("hostUserId") Long hostUserId)
    {
        return listingReviews.viewByHostUserId(hostUserId);
    }

}
