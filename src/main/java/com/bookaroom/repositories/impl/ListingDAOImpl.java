package com.bookaroom.repositories.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bookaroom.entities.ListingDTO;
import com.bookaroom.repositories.ListingDAOCustom;

public class ListingDAOImpl implements ListingDAOCustom
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> findAllShortViews()
    {
        Query query = em.createNamedQuery(ListingDTO.QUERY_NAME_FIND_ALL_SHORT_VIEWS);

        return query.getResultList();
    }

    @Override
    public List<Object[]> findUserRecommendedShortViews(Long userId, int maxDaysAfterSearch, int maxResults)
    {
        Query query = em.createNamedQuery(ListingDTO.QUERY_NAME_FIND_USER_RECOMMENDED_SHORT_VIEWS);
        

        query.setParameter(ListingDTO.QUERY_PARAM_RECOMM_INDEX_USER_ID, userId);
        query.setParameter(ListingDTO.QUERY_PARAM_RECOMM_INDEX_MAX_DAYS_AFTER_SEARCH, maxDaysAfterSearch);
        query.setParameter(ListingDTO.QUERY_PARAM_RECOMM_INDEX_MAX_RESULTS, maxResults);

        return query.getResultList();
    }

    @Override
    public List<Object[]> searchShortViews(
        String state,
        String city,
        String country,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
    {
        Query query = em.createNamedQuery(ListingDTO.QUERY_NAME_SEARCH_SHORT_VIEWS);

        query.setParameter(ListingDTO.QUERY_PARAM_SEARCH_INDEX_STATE, state);
        query.setParameter(ListingDTO.QUERY_PARAM_SEARCH_INDEX_CITY, city);
        query.setParameter(ListingDTO.QUERY_PARAM_SEARCH_INDEX_COUNTRY, country);
        query.setParameter(ListingDTO.QUERY_PARAM_SEARCH_INDEX_CHECK_IN, checkIn);
        query.setParameter(ListingDTO.QUERY_PARAM_SEARCH_INDEX_CHECK_OUT, checkOut);
        query.setParameter(ListingDTO.QUERY_PARAM_SEARCH_INDEX_NUM_OF_GUESTS, numberOfGuests);

        return query.getResultList();
    }

    @Override
    public boolean isAvailableOnDates(Long listingId, Date checkIn, Date checkOut)
    {
        Query query = em.createNamedQuery(ListingDTO.QUERY_NAME_GET_IF_IS_AVAILABLE);

        query.setParameter(ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_LISTING_ID, listingId);
        query.setParameter(ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_IN, checkIn);
        query.setParameter(ListingDTO.QUERY_PARAM_IS_AVAILABLE_INDEX_CHECK_OUT, checkOut);

        List<Object[]> results = query.getResultList();

        return results != null && !results.isEmpty();
    }

}
