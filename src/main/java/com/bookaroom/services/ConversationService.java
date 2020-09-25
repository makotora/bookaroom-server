package com.bookaroom.services;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.bookaroom.entities.ConversationDTO;
import com.bookaroom.enums.UserRole;
import com.bookaroom.exceptions.ConversationNotFoundException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.web.dto.ConversationCreationResponse;
import com.bookaroom.web.dto.ConversationResponse;

public interface ConversationService {
    public ConversationCreationResponse getOrCreateDiscussion(Principal userAPrincipal, Long userB)
        throws UserNotFoundException, UserNotAuthenticatedException;

    public ConversationCreationResponse getOrCreateDiscussion(Long userA, Long userB);

    public ConversationDTO findByUserAAndUserB(Long userA, Long userB);

    public List<ConversationDTO> findAll();

    public List<ConversationResponse> findByUserAAndUserBRoleDesc(
        Principal userAPrincipal,
        UserRole userBRole)
        throws UserNotFoundException, UserNotAuthenticatedException;

    public List<ConversationResponse> findByUserAAndUserBRoleDesc(Long userId, UserRole userBRole);

    public ConversationDTO updateLastAccessDate(Long conversationId, Date lastAccessDate)
        throws ConversationNotFoundException;
}
