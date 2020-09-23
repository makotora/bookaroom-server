package com.bookaroom.repositories;

import java.util.Date;
import java.util.List;

public interface ListingDAOCustom
{
    public List<Object[]> findAllShortViews();

    public List<Object[]> findUserRecommendedShortViews(Long userId, int maxDaysAfterSearch, int maxResults);

    public List<Object[]> searchShortViews(
        String state,
        String city,
        String country,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests);
}
