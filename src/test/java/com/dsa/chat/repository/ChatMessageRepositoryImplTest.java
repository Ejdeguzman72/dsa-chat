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



}