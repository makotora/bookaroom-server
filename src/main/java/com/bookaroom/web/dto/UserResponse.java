package com.bookaroom.web.dto;

public class UserResponse
{
    private Long id;

    private String username;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private String userRoleStr;

    private String details;

    private String picturePath;

    public UserResponse(
        Long id,
        String username,
        String name,
        String surname,
        String email,
        String phone,
        String userRoleStr,
        String details,
        String picturePath)
    {
        super();
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.userRoleStr = userRoleStr;
        this.details = details;
        this.picturePath = picturePath;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getUserRoleStr()
    {
        return userRoleStr;
    }

    public void setUserRoleStr(String userRoleStr)
    {
        this.userRoleStr = userRoleStr;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getPicturePath()
    {
        return picturePath;
    }

    public void setPicturePath(String picturePath)
    {
        this.picturePath = picturePath;
    }

    @Override
    public String toString()
    {
        return "UserResponse [id="
               + id
               + ", username="
               + username
               + ", name="
               + name
               + ", surname="
               + surname
               + ", email="
               + email
               + ", phone="
               + phone
               + ", userRoleStr="
               + userRoleStr
               + ", details="
               + details
               + ", picturePath="
               + picturePath
               + "]";
    }

}
