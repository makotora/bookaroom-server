package com.bookaroom.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookaroom.entities.ConversationDTO;

@Repository
public interface ConversationDAO extends JpaRepository<ConversationDTO, Long>, ConversationDAOCustom
{
    ConversationDTO findById(Long id);

    List<ConversationDTO> findByUserAOrUserB(Long userA, Long userB, Sort sort);

    @Query("SELECT d from ConversationDTO d WHERE (d.userA = :userA and d.userB = :userB)"
           + "or (d.userA = :userB and d.userB = :userA)")
    public List<ConversationDTO> findConversationOfUsers(
        @Param("userA") Long userA,
        @Param("userB") Long userB);

}
