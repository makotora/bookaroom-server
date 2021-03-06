package com.bookaroom.web.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bookaroom.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AvailabilityRange implements Serializable
{
    private static final long serialVersionUID = 1L;

    @JsonSerialize(as = Date.class)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = Constants.DEFAULT_TIMEZONE)
    private Date from;

    @JsonSerialize(as = Date.class)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = Constants.DEFAULT_TIMEZONE)
    private Date to;

    public AvailabilityRange()
    {}

    public AvailabilityRange(Date from, Date to)
    {
        super();
        this.from = from;
        this.to = to;
    }

    public static AvailabilityRange fromRequestString(String requestString)
    {
        String[] fromToParts = requestString.split(",");
        try {
            return new AvailabilityRange(new SimpleDateFormat("dd/MM/yyyy").parse(fromToParts[0]),
                                         new SimpleDateFormat("dd/MM/yyyy").parse(fromToParts[1]));
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date getFrom()
    {
        return from;
    }

    public void setFrom(Date from)
    {
        this.from = from;
    }

    public Date getTo()
    {
        return to;
    }

    public void setTo(Date to)
    {
        this.to = to;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((from == null) ? 0 : from.hashCode());
        result = prime * result + ((to == null) ? 0 : to.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AvailabilityRange other = (AvailabilityRange) obj;
        if (from == null) {
            if (other.from != null)
                return false;
        }
        else if (!from.equals(other.from))
            return false;
        if (to == null) {
            if (other.to != null)
                return false;
        }
        else if (!to.equals(other.to))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "AvailabilityRange [from=" + from + ", to=" + to + "]";
    }

}
