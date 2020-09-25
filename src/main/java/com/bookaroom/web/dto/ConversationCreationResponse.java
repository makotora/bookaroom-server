package com.bookaroom.web.dto;

public class ConversationCreationResponse
{
    private Long conversationId;

    public ConversationCreationResponse(Long conversationId)
    {
        super();
        this.conversationId = conversationId;
    }

    public Long getConversationId()
    {
        return conversationId;
    }

    public void setConversationId(Long conversationId)
    {
        this.conversationId = conversationId;
    }

    @Override
    public String toString()
    {
        return "ConversationResponse [conversationId=" + conversationId + "]";
    }

}
