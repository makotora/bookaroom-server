package com.bookaroom.repositories.impl;

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
        

        query.setParameter(ListingDTO.QUERY_PARAM_INDEX_USER_ID, userId);
        query.setParameter(ListingDTO.QUERY_PARAM_INDEX_MAX_DAYS_AFTER_SEARCH, maxDaysAfterSearch);
        query.setParameter(ListingDTO.QUERY_PARAM_INDEX_MAX_RESULTS, maxResults);

        return query.getResultList();
    }

}
