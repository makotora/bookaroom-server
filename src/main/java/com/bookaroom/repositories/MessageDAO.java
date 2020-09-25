package com.bookaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.MessageDTO;

@Repository
public interface MessageDAO extends JpaRepository<MessageDTO, Long>, MessagesDAOCustom
{
    public MessageDTO findById(Long id);
}
