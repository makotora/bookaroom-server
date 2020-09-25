package com.bookaroom.web.endpoints;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookaroom.enums.UserRole;
import com.bookaroom.exceptions.ConversationNotFoundException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.services.ConversationService;
import com.bookaroom.services.MessageService;
import com.bookaroom.web.dto.ActionResponse;
import com.bookaroom.web.dto.ConversationCreationResponse;
import com.bookaroom.web.dto.ConversationMessageResponse;
import com.bookaroom.web.dto.ConversationResponse;
import com.bookaroom.web.dto.SendMessageRequest;

@RestController
@RequestMapping("messages")
public class MessagesRestEndpoint
{
    private static Logger log = LoggerFactory.getLogger(MessagesRestEndpoint.class);

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void handle(Exception e)
    {
        log.warn("Returning HTTP 400 Bad Request", e);
    }

    @Autowired
    private ConversationService conversations;

    @Autowired
    private MessageService messages;

    @RequestMapping(
        value = "/getOrCreateConversation",
        method = RequestMethod.GET)
    public ConversationCreationResponse getOrCreateConversation(
        Principal principal,
        @RequestParam("userB") Long userB)
    {
        try {
            return conversations.getOrCreateDiscussion(principal, userB);
        }
        catch (UserNotFoundException | UserNotAuthenticatedException e) {
            return null;
        }
    }

    @RequestMapping(value = "/getByUserAAndUserBRole", method = RequestMethod.GET)
    public List<ConversationResponse> getByUserAAndUserBRole(
        Principal principal,
        @RequestParam("userBRoleString") String userBRoleString)
    {
        UserRole userBRole = null;
        try {
            userBRole = UserRole.valueOf(userBRoleString);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ConversationResponse>();
        }

        try {
            return conversations.findByUserAAndUserBRoleDesc(principal, userBRole);
        }
        catch (UserNotFoundException | UserNotAuthenticatedException e) {
            e.printStackTrace();
            return new ArrayList<ConversationResponse>();
        }
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public ActionResponse sendMessage(Principal principal, @RequestBody SendMessageRequest sendMessageRequest)
    {
        try {
            messages.sendMessage(principal,
                                 sendMessageRequest.getMessage(),
                                 sendMessageRequest.getConversationId());
        }
        catch (ConversationNotFoundException e) {
            return new ActionResponse(false, "No such conversation");
        }
        catch (UserNotFoundException e) {
            return new ActionResponse(false, "Invalid user");
        }
        catch (UserNotAuthenticatedException e) {
            return new ActionResponse(false, "Please login again");
        }

        return new ActionResponse(true, "Message sent");
    }

    @RequestMapping(value = "/getConversationMessages", method = RequestMethod.GET)
    public List<ConversationMessageResponse> getConversationMessages(Principal principal, @RequestParam("conversationId") Long conversationId) {
        return messages.getConversationMessages(conversationId);
    }
}
