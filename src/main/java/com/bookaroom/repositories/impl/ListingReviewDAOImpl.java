package com.bookaroom.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bookaroom.entities.ListingReviewDTO;
import com.bookaroom.repositories.ListingReviewDAOCustom;

public class ListingReviewDAOImpl implements ListingReviewDAOCustom
{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> findHostReviews(Long hostUserId)
    {
        Query query = em.createNamedQuery(ListingReviewDTO.QUERY_NAME_FIND_HOST_REVIEWS);

        query.setParameter(1, hostUserId);

        return query.getResultList();
    }

}
