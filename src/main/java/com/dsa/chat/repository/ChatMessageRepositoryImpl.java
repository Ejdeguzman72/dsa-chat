package com.dsa.chat.repository;

import com.dsa.chat.domain.ChatMessageSearchResponse;
import com.dsa.chat.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatMessageRepositoryImpl implements ChatMessageRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ChatMessage> retrieveAllMessages() {
        return List.of();
    }

    @Override
    public ChatMessageSearchResponse retrieveChatMessageById(long chatMessageId) {
        return null;
    }

    @Override
    public int addNewMessage(ChatMessage request) {
        return 0;
    }

    @Override
    public int updateMessage(ChatMessage request) {
        return 0;
    }

    @Override
    public int deleteChatMessage(long chatMessageId) {
        return 0;
    }
}
