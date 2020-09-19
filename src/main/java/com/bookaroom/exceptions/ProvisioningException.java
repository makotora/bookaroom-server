package com.bookaroom.exceptions;

public class ProvisioningException extends Exception
{

    private static final long serialVersionUID = 1L;

    public ProvisioningException(String errorMessage)
    {
        super(errorMessage);
    }

}
