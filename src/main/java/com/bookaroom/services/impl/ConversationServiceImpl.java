package com.bookaroom.services.impl;

import java.math.BigInteger;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookaroom.entities.ConversationDTO;
import com.bookaroom.entities.UserDTO;
import com.bookaroom.enums.UserRole;
import com.bookaroom.exceptions.ConversationNotFoundException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.repositories.ConversationDAO;
import com.bookaroom.services.ConversationService;
import com.bookaroom.services.UserService;
import com.bookaroom.util.Utils;
import com.bookaroom.web.dto.ConversationCreationResponse;
import com.bookaroom.web.dto.ConversationResponse;

@Service
public class ConversationServiceImpl implements ConversationService
{

	private static Logger log = LoggerFactory.getLogger(ConversationServiceImpl.class);

	@Autowired
    private ConversationDAO conversationDAO;
	
	@Autowired
    private UserService users;

    @Override
    @Transactional
    public ConversationCreationResponse getOrCreateDiscussion(Principal userAPrincipal, Long userB)
        throws UserNotFoundException, UserNotAuthenticatedException
    {
        UserDTO userA = users.findByPrincipal(userAPrincipal);

        return getOrCreateDiscussion(userA.getId(), userB);
    }
	
	@Override
	@Transactional
    public ConversationCreationResponse getOrCreateDiscussion(Long userA, Long userB)
    {
	    ConversationDTO existingConversation = findByUserAAndUserB(userA, userB);
	    if (existingConversation != null) {
            return new ConversationCreationResponse(existingConversation.getId());
	    }
	    
        ConversationDTO conversation = new ConversationDTO();
        conversation.setUserA(userA);
        conversation.setUserB(userB);
        conversation.setLastAccess(new Date());
		
        conversationDAO.save(conversation);
        return new ConversationCreationResponse(conversation.getId());
	}

    @Override
    public ConversationDTO findByUserAAndUserB(Long userA, Long userB)
    {
        List<ConversationDTO> conversations = conversationDAO.findConversationOfUsers(userA, userB);

        return conversations != null && !conversations.isEmpty() ? conversations.get(0) : null;
    }

	@Override
    public List<ConversationDTO> findAll()
    {
        return conversationDAO.findAll();
	}

    @Override
    public List<ConversationResponse> findByUserAAndUserBRoleDesc(
        Principal userAPrincipal,
        UserRole userBRole)
        throws UserNotFoundException, UserNotAuthenticatedException
    {
        UserDTO userA = users.findByPrincipal(userAPrincipal);

        return findByUserAAndUserBRoleDesc(userA.getId(), userBRole);
    }

	@Override
    public List<ConversationResponse> findByUserAAndUserBRoleDesc(Long userId, UserRole userBRole)
    {
        return conversationDAO.findByUserAAndUserBRoleOrderByDateDesc(userId, userBRole)
                              .stream()
                              .map(cols -> new ConversationResponse(((BigInteger) cols[0]).longValue(),
                                                                    (String) cols[1],
                                                                    Utils.prepareUserPicturePath((String) cols[2]),
                                                                    (String) cols[3],
                                                                    (String) cols[4]))
                              .collect(Collectors.toList());
	}

    @Override
    public ConversationDTO updateLastAccessDate(Long conversationId, Date lastAccessDate)
        throws ConversationNotFoundException
    {
        ConversationDTO conversation = conversationDAO.findById(conversationId);

        if (conversation == null) {
            throw new ConversationNotFoundException();
        }

        conversation.setLastAccess(lastAccessDate);

        conversationDAO.saveAndFlush(conversation);

        return conversation;
    }

}
