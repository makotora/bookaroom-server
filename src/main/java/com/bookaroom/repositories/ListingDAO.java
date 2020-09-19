package com.bookaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.ListingDTO;

@Repository
public interface ListingDAO extends JpaRepository<ListingDTO, Long>, JpaSpecificationExecutor<ListingDTO>
{}
