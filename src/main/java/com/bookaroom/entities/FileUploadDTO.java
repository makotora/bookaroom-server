package com.bookaroom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = FileUploadDTO.TABLE_NAME)
public class FileUploadDTO
{
    public static final String TABLE_NAME = "FILE_UPLOADS";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SERVER_PATH")
    private String serverPath;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    public FileUploadDTO()
    {}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getServerPath()
    {
        return serverPath;
    }

    public void setServerPath(String serverPath)
    {
        this.serverPath = serverPath;
    }

    public String getOriginalName()
    {
        return originalName;
    }

    public void setOriginalName(String originalName)
    {
        this.originalName = originalName;
    }

    @Override
    public String toString()
    {
        return "FileDetails [id="
               + id
               + ", serverPath="
               + serverPath
               + ", originalName="
               + originalName
               + "]";
    }
}
