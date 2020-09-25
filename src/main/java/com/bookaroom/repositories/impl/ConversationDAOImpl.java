package com.bookaroom.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bookaroom.entities.ConversationDTO;
import com.bookaroom.enums.UserRole;
import com.bookaroom.repositories.ConversationDAOCustom;

public class ConversationDAOImpl implements ConversationDAOCustom
{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> findByUserAAndUserBRoleOrderByDateDesc(Long userA, UserRole userBRole)
    {
        Query query = em.createNamedQuery(ConversationDTO.QUERY_NAME_FIND_BY_A_AND_B_ROLE);

        query.setParameter(1, userA);
        query.setParameter(2, userBRole.getValue());

        return query.getResultList();
    }

}
