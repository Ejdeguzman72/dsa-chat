package com.dsa.chat.domain;

import com.dsa.chat.entity.ChatMessage;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ChatMessageMapper implements RowMapper<ChatMessage> {
    @Override
    public ChatMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(rs.getString("CONTENT"));
        chatMessage.setSentDatetime(rs.getTimestamp("SENT_DATETIME").toLocalDateTime());

        return chatMessage;
    }
}
