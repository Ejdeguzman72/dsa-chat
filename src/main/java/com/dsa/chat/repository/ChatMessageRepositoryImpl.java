package com.dsa.chat.repository;

import com.dsa.chat.domain.ChatMessageMapper;
import com.dsa.chat.domain.ChatMessageSearchResponse;
import com.dsa.chat.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
    public static final String UPDATE_CHAT_MESSAGE = "UPDATE CHAT_MESSAGE SET CONTENT = ?, SENT_DATETIME = ? WHERE USER_ID = ?";
    public static final String DELETE_CHAT_MESSAGE = "DELETE FROM CHAT_MESSAGE WHERE USER_ID = ?";
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
        int result = 0;
        if (request != null) {
            ChatMessage chatMessage = retrieveChatMessageById(request.getChatMessageId());
            if (chatMessage != null) {
                ChatMessage updatedChatMessage = new ChatMessage();
                updatedChatMessage.setChatMessageId(request.getChatMessageId());
                updatedChatMessage.setContent(request.getContent());
                updatedChatMessage.setSentDatetime(LocalDateTime.now());
                result = jdbcTemplate.update(UPDATE_CHAT_MESSAGE,new Object[] {
                        updatedChatMessage.getContent(),
                        updatedChatMessage.getSentDatetime(),
                        updatedChatMessage.getChatMessageId()
                });
            }
        }
        return result;
    }

    @Override
    public int deleteChatMessage(long chatMessageId) {
        int result = 0;
        if (chatMessageId > 0) {
            result = jdbcTemplate.update(DELETE_CHAT_MESSAGE,chatMessageId);
        }

        return result;
    }
}
