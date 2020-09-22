package com.bookaroom.services;

import java.util.Date;
import java.util.List;

import com.bookaroom.entities.SearchDTO;

public interface SearchService
{

    public SearchDTO saveSearch(
        Long userId,
        String state,
        String city,
        String country,
        Date checkIn,
        Date checkOut,
        Integer numberOfGuests);

    List<SearchDTO> findAll();

    List<SearchDTO> findByUserId(Long userId);

}
