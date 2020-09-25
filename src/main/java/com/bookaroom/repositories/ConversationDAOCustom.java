package com.bookaroom.repositories;

import java.util.List;

import com.bookaroom.enums.UserRole;

public interface ConversationDAOCustom
{
    List<Object[]> findByUserAAndUserBRoleOrderByDateDesc(Long userA, UserRole userBRole);

}
