package com.bookaroom.web.dto;

public class ActionResponse
{

    private boolean success;
    private String message;

    public ActionResponse(boolean success, String message)
    {
        super();
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
