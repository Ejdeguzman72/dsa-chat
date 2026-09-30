package com.dsa.chat.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ChatMessageMapper implements RowMapper<ChatMessage> {
    @Override
    public ChatMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(rs.getString("CONTENT"));
        chatMessage.setSender(rs.getString("SENDER"));
        chatMessage.setSentDatetime(LocalDateTime.from(rs.getDate("LAST_UPDATETIME").toLocalDate()));

        return chatMessage;
    }
}
