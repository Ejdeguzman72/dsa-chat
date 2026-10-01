package com.dsa.chat.repository;

import com.dsa.chat.domain.ChatMessageMapper;
import com.dsa.chat.domain.ChatMessageSearchResponse;
import com.dsa.chat.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatMessageRepositoryImpl implements ChatMessageRepository {
    private JdbcTemplate jdbcTemplate;
    private ChatMessageMapper chatMessageMapper;
    @Autowired
    public ChatMessageRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            ChatMessageMapper chatMessageMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.chatMessageMapper = chatMessageMapper;
    }
    public static final String GET_ALL_MESSAGES = "SELECT CHAT_MESSAGE_ID,SENDER,CONTENT,SENT_DATETIME,USER_ID FROM CHAT_MESSAGE";
    public static final String GET_ALL_MESSAGES_BY_USER_ID = "SELECT CHAT_MESSAGE_ID,SENDER,CONTENT,SENT_DATETIME,USER_ID FROM CHAT_MESSAGE WHERE USER_ID = ?";
    public static final String GET_MESSAGE_BY_ID = "SELECT CHAT_MESSAGE_ID,SENDER,CONTENT,SENT_DATETIME,USER_ID FROM CHAT_MESSAGE WHERE CHAT_MESSAGE_ID = ?";
    public static final String ADD_NEW_CHAT_MSG = "INSERT INTO CHAT_MESSAGE(CONTENT, SENT_DATETIME, USER_ID) VALUES (?,?,?)";
    @Override
    public List<ChatMessage> retrieveAllMessages() {
        List<ChatMessage> list = new ArrayList<>();
        list = jdbcTemplate.query(GET_ALL_MESSAGES,chatMessageMapper);
        return list;
    }

    @Override
    public List<ChatMessage> retrieveAllMessagesByUserId(long userId) {
        return jdbcTemplate.query(GET_ALL_MESSAGES_BY_USER_ID,chatMessageMapper,userId);
    }

    @Override
    public ChatMessage retrieveChatMessageById(long chatMessageId) {
        try {
            ChatMessage chatMessage = jdbcTemplate.queryForObject(GET_MESSAGE_BY_ID,chatMessageMapper,chatMessageId);
            return chatMessage;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public int addNewMessage(ChatMessage request) {
        int result = 0;
        if (request != null) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent(request.getContent());
            chatMessage.setSentDatetime(request.getSentDatetime());
            chatMessage.setDsaUser(request.getDsaUser());

            result = jdbcTemplate.update(ADD_NEW_CHAT_MSG,new Object[]{
                    request.getContent(),
                    request.getSentDatetime(),
                    request.getDsaUser().getUserId()
            });
        }

        return result;
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
