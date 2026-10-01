package com.dsa.chat.repository;

import com.dsa.chat.domain.ChatMessageMapper;
import com.dsa.chat.domain.DSAUserMapper;
import com.dsa.chat.entity.ChatMessage;
import com.dsa.chat.entity.DsaUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatMessageRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private ChatMessageMapper chatMessageMapper;
    @InjectMocks
    private ChatMessageRepositoryImpl chatMessageRepository;
    @Test
    public void retrieveAllUsersTest() {
        List<ChatMessage> expected = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage();
        expected.add(chatMessage);

        when(jdbcTemplate.query(anyString(),eq(chatMessageMapper))).thenReturn(expected);

        List<ChatMessage> result = chatMessageRepository.retrieveAllMessages();

        assertEquals(expected,result);
    }
    @Test
    public void getAllChatMessagesByUserTest() {
        List<ChatMessage> expected = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setChatMessageId(1);
        chatMessage.setContent("This is a test content");
        chatMessage.setSentDatetime(LocalDateTime.now());
        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setChatMessageId(2);
        chatMessage1.setContent("This is more test content");
        chatMessage1.setSentDatetime(LocalDateTime.now());
        expected.add(chatMessage);
        expected.add(chatMessage1);

        when(jdbcTemplate.query(anyString(),eq(chatMessageMapper),eq(1L))).thenReturn(expected);

        List<ChatMessage> result = chatMessageRepository.retrieveAllMessagesByUserId(1);

        assertEquals(expected,result);
    }



}