package com.bookaroom.exceptions;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.WebFault;

@WebFault
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fault", propOrder = {"errorCode", "errorDescription"})
public class ServiceException extends Exception
{

    private static final long serialVersionUID = -2703581443022246694L;

    private Long errorCode;
    private String errorDescription;
    private List<Object> messageParameters;

    public ServiceException()
    {
        super();
    }

    public ServiceException(Throwable cause)
    {
        super(cause);
    }

    public ServiceException(Long errorCode, String errorDescription)
    {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public ServiceException(Long errorCode, String errorDescription, Throwable cause)
    {
        super(errorDescription, cause);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public ServiceException(
        String message,
        Long errorCode,
        String errorDescription,
        List<Object> messageParameters)
    {
        super(message);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.messageParameters = messageParameters;
    }

    public ServiceException(
        String message,
        Long errorCode,
        String errorDescription,
        List<Object> messageParameters,
        Throwable cause)
    {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.messageParameters = messageParameters;
    }

    public void setErrorCode(Long errorCode)
    {
        this.errorCode = errorCode;
    }

    public void setErrorDescription(String errorDescription)
    {
        this.errorDescription = errorDescription;
    }

    public Long getErrorCode()
    {
        return errorCode;
    }

    public String getErrorDescription()
    {
        return errorDescription;
    }

    public List<Object> getMessageParameters()
    {
        return messageParameters;
    }

    public void setMessageParameters(List<Object> messageParameters)
    {
        this.messageParameters = messageParameters;
    }

    @Override
    public String toString()
    {
        return "SWServiceException [errorCode="
               + errorCode
               + ", errorDescription="
               + errorDescription
               + ", messageParameters="
               + messageParameters
               + "]";
    }

}
