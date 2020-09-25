package com.bookaroom.services.impl;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookaroom.entities.MessageDTO;
import com.bookaroom.entities.UserDTO;
import com.bookaroom.exceptions.ConversationNotFoundException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.repositories.MessageDAO;
import com.bookaroom.services.ConversationService;
import com.bookaroom.services.MessageService;
import com.bookaroom.services.UserService;
import com.bookaroom.util.Utils;
import com.bookaroom.web.dto.ConversationMessageResponse;

@Service
public class MessagesServiceImpl implements MessageService
{

	@Autowired
    private MessageDAO messageDAO;

    @Autowired
    private ConversationService conversations;

    @Autowired
    private UserService users;

    @Override
    @Transactional
    public MessageDTO sendMessage(Principal principal, String message, Long conversationId)
        throws ConversationNotFoundException, UserNotFoundException, UserNotAuthenticatedException
    {
        UserDTO user = users.findByPrincipal(principal);

        return sendMessage(user.getId(), message, conversationId);
    }

	@Override
    @Transactional
    public MessageDTO sendMessage(Long userId, String message, Long conversationId)
        throws ConversationNotFoundException
    {
        MessageDTO newMessage = new MessageDTO();
		
        Date now = new Date();

		newMessage.setSender(userId);
        newMessage.setSeen(false);;
		newMessage.setMessage(message);
        newMessage.setDate(now);
        newMessage.setConversationId(conversationId);
        
        messageDAO.save(newMessage);
		
        conversations.updateLastAccessDate(conversationId, now);

		return newMessage;
	}

    @Override
    public List<ConversationMessageResponse> getConversationMessages(Long conversationId)
    {
        return messageDAO.findConversationMessages(conversationId)
                  .stream()
                         .map(cols -> new ConversationMessageResponse(Utils.prepareUserPicturePath((String) cols[0]),
                                                               (String) cols[1],
                                                               (String) cols[2]))
                  .collect(Collectors.toList());
    }
	
	
	
}
