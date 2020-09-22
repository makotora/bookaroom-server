package com.bookaroom.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookaroom.entities.SearchDTO;
import com.bookaroom.repositories.SearchDAO;
import com.bookaroom.services.SearchService;

@Service
public class SearchServiceImpl implements SearchService
{

    @Autowired
    SearchDAO searchDao;

    @Override
    @Transactional
    public SearchDTO saveSearch(
        Long userId,
        String state,
        String city,
        String country,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests)
    {
        SearchDTO search = new SearchDTO();
        search.setUserId(userId);
        search.setState(state);
        search.setCity(city);
        search.setCountry(country);
        search.setCheckIn(checkIn);
        search.setCheckOut(checkOut);
        search.setNumberOfGuests(numberOfGuests);
        search.setSearchDate(new Date());

        searchDao.save(search);

        return search;
    }

    @Override
    public List<SearchDTO> findAll()
    {
        return searchDao.findAll();
    }

    @Override
    public List<SearchDTO> findByUserId(Long userId)
    {
        return searchDao.findByUserId(userId);
    }


}
