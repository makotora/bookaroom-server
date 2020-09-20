package com.bookaroom.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.bookaroom.entities.ListingDTO;
import com.bookaroom.repositories.ListingDAOCustom;

public class ListingDAOCustomImpl implements ListingDAOCustom
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> findAllShortViews()
    {
        TypedQuery<Object[]> query = em.createNamedQuery(ListingDTO.QUERY_NAME_FIND_ALL_SHORT_VIEWS,
                                                         Object[].class);

        return query.getResultList();
    }


}
