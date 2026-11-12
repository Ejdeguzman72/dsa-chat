package com.dsa.chat.domain;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChatMessageDTOMapper implements RowMapper<ChatMessageDTO> {

    @Override
    public ChatMessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setChatMessageId(rs.getLong("CHAT_MESSAGE_ID"));
        chatMessageDTO.setUsername(rs.getString("USERNAME"));
        chatMessageDTO.setContent(rs.getString("CONTENT"));
        chatMessageDTO.setSentDatetime(rs.getTimestamp("SENT_DATETIME").toLocalDateTime());
//        chatMessageDTO.setCreateTime(rs.getTimestamp("CREATE_TIME").toLocalDateTime());
//        chatMessageDTO.setLastUpdateTime(rs.getTimestamp("LAST_UPDATEDATETIME").toLocalDateTime());

        return chatMessageDTO;
    }
}
