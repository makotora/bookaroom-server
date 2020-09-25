package com.bookaroom.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bookaroom.entities.MessageDTO;
import com.bookaroom.repositories.MessagesDAOCustom;

public class MessageDAOImpl implements MessagesDAOCustom
{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> findConversationMessages(Long conversationId)
    {
        Query query = em.createNamedQuery(MessageDTO.QUERY_NAME_FIND_CONVERSATION_MESSAGES);

        query.setParameter(1, conversationId);

        return query.getResultList();
    }

}
