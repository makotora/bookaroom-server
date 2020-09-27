package com.bookaroom.web.dto;

public class UserProfileResponse
{
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String picturePath;

    public UserProfileResponse(String name, String surname, String email, String phone, String picturePath)
    {
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.picturePath = picturePath;
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
        return "UserProfileResponse [name="
               + name
               + ", surname="
               + surname
               + ", email="
               + email
               + ", phone="
               + phone
               + ", picturePath="
               + picturePath
               + "]";
    }

}