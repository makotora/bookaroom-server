package com.bookaroom.web.dto;

public class ConversationMessageResponse
{
    private String userPicturePath;
    private String userName;
    private String message;

    public ConversationMessageResponse(String userPicturePath, String userName, String message)
    {
        super();
        this.userPicturePath = userPicturePath;
        this.userName = userName;
        this.message = message;
    }

    public String getUserPicturePath()
    {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath)
    {
        this.userPicturePath = userPicturePath;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
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
        return "MessageResponse [userPicturePath="
               + userPicturePath
               + ", userName="
               + userName
               + ", message="
               + message
               + "]";
    }

}
