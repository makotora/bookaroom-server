package com.bookaroom.exceptions;

public class StringConversionException extends RuntimeException
{

    private static final long serialVersionUID = 1066190124634162435L;

    public StringConversionException()
    {
        super();
    }

    public StringConversionException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StringConversionException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public StringConversionException(String message)
    {
        super(message);
    }

    public StringConversionException(Throwable cause)
    {
        super(cause);
    }

}
