package com.bookaroom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.SearchDTO;

@Repository
public interface SearchDAO extends JpaRepository<SearchDTO, Long>
{
    List<SearchDTO> findByUserId(Long userId);
}
