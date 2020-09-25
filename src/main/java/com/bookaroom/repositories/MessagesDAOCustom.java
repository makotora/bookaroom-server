package com.bookaroom.repositories;

import java.util.List;

public interface MessagesDAOCustom
{
    List<Object[]> findConversationMessages(Long conversationId);
}
