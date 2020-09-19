package com.bookaroom.services;

import java.util.Date;
import java.util.List;

import com.bookaroom.entities.SearchDTO;

public interface Searches
{

    public SearchDTO addSearch(
        Integer bathrooms,
        Integer bedrooms,
        Integer beds,
        String price,
        String user,
        String state,
        String city,
        String country,
        Date beginDate,
        Date endDate);

    List<SearchDTO> findAll();

}
