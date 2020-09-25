package com.bookaroom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.UserDTO;

@Repository
public interface UserDAO extends JpaRepository<UserDTO, Long>
{

    UserDTO findByUsername(String username);

    UserDTO findById(long id);

    List<UserDTO> findByEmail(String email);

    List<UserDTO> findByListingId(Long listingId);

}
