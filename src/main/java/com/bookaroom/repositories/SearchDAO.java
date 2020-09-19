package com.bookaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.SearchDTO;

@Repository
public interface SearchDAO extends JpaRepository<SearchDTO, Long>, JpaSpecificationExecutor<SearchDTO>
{

}
