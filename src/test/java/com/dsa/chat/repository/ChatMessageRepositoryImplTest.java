package com.dsa.chat.repository;

import com.dsa.chat.domain.ChatMessageMapper;
import com.dsa.chat.domain.ChatMessage;
import com.dsa.chat.domain.DsaUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

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

    @Test
    public void retrieveChatMessageByIdTest() {
        ChatMessage expected = new ChatMessage();
        expected.setChatMessageId(1);
        expected.setContent("This is a test content");
        expected.setSentDatetime(LocalDateTime.now());

        when(jdbcTemplate.queryForObject(anyString(),eq(chatMessageMapper),eq(1L))).thenReturn(expected);

        ChatMessage result = chatMessageRepository.retrieveChatMessageById(1);

        assertEquals(expected,result);
    }

    @Test
    public void addNewMessageTest() {
        ChatMessage expected = new ChatMessage();
        expected.setChatMessageId(1000);
        expected.setContent("This is another test content, this is another test content");
        expected.setSentDatetime(LocalDateTime.now());
        DsaUser dsaUser = new DsaUser();
        dsaUser.setUserId(1500);
        dsaUser.setUsername("testuser");
        dsaUser.setPassword("testpassword");
        expected.setDsaUser(dsaUser);

        when(jdbcTemplate.update(anyString(),any(Object[].class))).thenReturn(1);

        int result = chatMessageRepository.addNewMessage(expected);

        assertEquals(1,result);
    }

    @Test
    public void updateMessageTest() {
        ChatMessage existing = new ChatMessage();
        existing.setChatMessageId(1000);
        existing.setContent("This is another test content, this is another test content");
        existing.setSentDatetime(LocalDateTime.now());

        DsaUser dsaUser = new DsaUser();
        dsaUser.setUserId(1500);
        dsaUser.setUsername("testuser");
        dsaUser.setPassword("testpassword");
        existing.setDsaUser(dsaUser);

        ChatMessage request = new ChatMessage();
        request.setChatMessageId(1000);
        request.setContent("This is another test test content, this is another test content");
        request.setSentDatetime(LocalDateTime.now());

        request.setDsaUser(dsaUser);

        when(jdbcTemplate.queryForObject(anyString(),eq(chatMessageMapper),eq(1000L))).thenReturn(existing);
        when(jdbcTemplate.update(anyString(),any(Object[].class))).thenReturn(1);


        int result = chatMessageRepository.updateMessage(request);

        assertEquals(1,result);
    }

    @Test
    public void deleteDsaUserTestTest() {
        long chatMessageId = 5000L;

        when(jdbcTemplate.update(anyString(),eq(chatMessageId))).thenReturn(1);

        int result = chatMessageRepository.deleteChatMessage(chatMessageId);
        assertEquals(1,result);
    }
}