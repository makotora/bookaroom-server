package com.bookaroom.repositories;

import java.util.List;

public interface ListingDAOCustom
{
    public List<Object[]> findAllShortViews();

    public List<Object[]> findUserRecommendedShortViews(Long userId, int maxDaysAfterSearch, int maxResults);
}
