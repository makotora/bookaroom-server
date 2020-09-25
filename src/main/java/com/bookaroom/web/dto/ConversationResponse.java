package com.bookaroom.web.dto;

public class ConversationResponse
{
    private Long conversationId;

    private String userBName;
    private String userBPicturePath;

    private String lastMessage;
    private String lastMessageUserName;

    public ConversationResponse(
        Long conversationId,
        String userBName,
        String userBPicturePath,
        String lastMessage,
        String lastMessageUserName)
    {
        super();
        this.conversationId = conversationId;
        this.userBName = userBName;
        this.userBPicturePath = userBPicturePath;
        this.lastMessage = lastMessage;
        this.lastMessageUserName = lastMessageUserName;
    }

    public Long getConversationId()
    {
        return conversationId;
    }

    public void setConversationId(Long conversationId)
    {
        this.conversationId = conversationId;
    }

    public String getUserBName()
    {
        return userBName;
    }

    public void setUserBName(String userBName)
    {
        this.userBName = userBName;
    }

    public String getUserBPicturePath()
    {
        return userBPicturePath;
    }

    public void setUserBPicturePath(String userBPicturePath)
    {
        this.userBPicturePath = userBPicturePath;
    }

    public String getLastMessage()
    {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage)
    {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageUserName()
    {
        return lastMessageUserName;
    }

    public void setLastMessageUserName(String lastMessageUserName)
    {
        this.lastMessageUserName = lastMessageUserName;
    }

    @Override
    public String toString()
    {
        return "ConversationResponse [conversationId="
               + conversationId
               + ", userBName="
               + userBName
               + ", userBPicturePath="
               + userBPicturePath
               + ", lastMessage="
               + lastMessage
               + ", lastMessageUserName="
               + lastMessageUserName
               + "]";
    }

}
