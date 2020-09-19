package com.bookaroom.enums;

import java.util.ArrayList;
import java.util.List;

public enum UserRole
{
    Guest(0),
    Host(1);

    private int value;

    UserRole(int value)
    {
        this.value = value;
    }

    public static List<String> names()
    {
        List<String> names = new ArrayList<>();
        for (UserRole userRole : UserRole.values()) {
            names.add(userRole.name());
        }

        return names;
    }

    public int getValue()
    {
        return value;
    }

}
