package com.bookaroom.enums;

public enum ListingType
{
    ROOM(0),
    HOUSE(1);

    private long value;

    private ListingType(long value)
    {
        this.value = value;
    }

    public long getValue()
    {
        return value;
    }

}
