package com.bookaroom.repositories;

import java.util.List;

public interface ListingReviewDAOCustom
{
    public List<Object[]> findHostReviews(Long hostUserId);
}
