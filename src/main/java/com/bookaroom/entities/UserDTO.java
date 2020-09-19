package com.bookaroom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bookaroom.enums.UserRole;

@Entity
@Table(name = "USERS")
public class UserDTO
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ROLE")
    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "PICTURE_FILE_UPLOAD_ID")
    private Long pictureFileUploadId;

    @Column(name = "LISTING_ID")
    private Long listingId;

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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public UserRole getUserRole()
    {
        return userRole;
    }

    public void setUserRole(UserRole userRole)
    {
        this.userRole = userRole;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public Long getPictureFileUploadId()
    {
        return pictureFileUploadId;
    }

    public void setPictureFileUploadId(Long pictureFileUploadId)
    {
        this.pictureFileUploadId = pictureFileUploadId;
    }

    public Long getListingId()
    {
        return listingId;
    }

    public void setListingId(Long listingId)
    {
        this.listingId = listingId;
    }

    @Override
    public String toString()
    {
        return "UserDTO [id="
               + id
               + ", username="
               + username
               + ", password="
               + password
               + ", name="
               + name
               + ", surname="
               + surname
               + ", email="
               + email
               + ", phone="
               + phone
               + ", userRole="
               + userRole
               + ", details="
               + details
               + ", pictureFileUploadId="
               + pictureFileUploadId
               + ", listingId="
               + listingId
               + "]";
    }

}
