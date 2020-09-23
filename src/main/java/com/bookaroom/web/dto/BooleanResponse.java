package com.bookaroom.web.dto;

public class BooleanResponse
{
    private boolean flag;

    public BooleanResponse(boolean flag)
    {
        this.flag = flag;
    }

    public BooleanResponse()
    {}

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    @Override
    public String toString()
    {
        return "BooleanResponse [flag=" + flag + "]";
    }

}
