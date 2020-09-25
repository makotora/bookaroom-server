package com.bookaroom.web.dto;

import java.io.Serializable;

public class SendMessageRequest implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long conversationId;
    private String message;

    public SendMessageRequest()
    {
        super();
    }

    public SendMessageRequest(Long conversationId, String message)
    {
        super();
        this.conversationId = conversationId;
        this.message = message;
    }

    public Long getConversationId()
    {
        return conversationId;
    }

    public void setConversationId(Long conversationId)
    {
        this.conversationId = conversationId;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "SendMessageRequest [conversationId=" + conversationId + ", message=" + message + "]";
    }

}
