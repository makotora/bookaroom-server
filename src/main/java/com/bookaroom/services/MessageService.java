package com.bookaroom.services;

import java.security.Principal;
import java.util.List;

import com.bookaroom.entities.MessageDTO;
import com.bookaroom.exceptions.ConversationNotFoundException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.web.dto.ConversationMessageResponse;

public interface MessageService {
    public MessageDTO sendMessage(Principal principal, String message, Long conversationId)
        throws ConversationNotFoundException, UserNotFoundException, UserNotAuthenticatedException;

    public MessageDTO sendMessage(Long userId, String message, Long conversationId)
        throws ConversationNotFoundException;

    public List<ConversationMessageResponse> getConversationMessages(Long conversationId);
}
