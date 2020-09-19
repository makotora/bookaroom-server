package com.bookaroom.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookaroom.entities.SearchDTO;
import com.bookaroom.repositories.SearchDAO;
import com.bookaroom.services.Searches;

@Service
public class SearchImpl implements Searches
{

    @Autowired
    SearchDAO searchDao;

    @Override
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
        Date endDate)
    {
        SearchDTO s = new SearchDTO();
        s.setBathrooms(bathrooms);
        s.setBedrooms(bedrooms);
        s.setBeds(beds);
        s.setBeginDate(beginDate);
        s.setCity(city);
        s.setCountry(country);
        s.setEndDate(endDate);
        s.setState(state);
        s.setPrice(price);
        s.setUser(user);

        searchDao.save(s);
        return s;
    }

    @Override
    public List<SearchDTO> findAll()
    {
        return searchDao.findAll();
    }

}
