package com.dsa.chat.service;

import com.dsa.chat.domain.ChatMessage;
import com.dsa.chat.domain.ChatMessageListResponse;
import com.dsa.chat.domain.DsaUser;
import com.dsa.chat.repository.ChatMessageRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatMessageServiceTest {
    @Mock
    private ChatMessageRepositoryImpl chatMessageRepository;
    @InjectMocks
    private ChatMessageService chatMessageService;
    ChatMessage testData;
    @BeforeEach
    void initializeTest() {
        testData = new ChatMessage();
        DsaUser user = new DsaUser();
        user.setUserId(100000);
        user.setUsername("TEST USERNAME");
        user.setPassword("TEST PASSWORD");
        user.setEmail("123@gmail.com");
        user.setCreationDate(LocalDate.now());
        user.setLastUpdatetime(LocalDate.now());

        testData.setDsaUser(user);
        testData.setContent("This is test content. This is a test message for junit test. ");
        testData.setSentDatetime(LocalDateTime.now());
    }
    @Test
    public void getAllChatMessages_successTest() {
        ChatMessage data = testData;
        List<ChatMessage> expected = List.of(data);

        when(chatMessageRepository.retrieveAllMessages()).thenReturn(expected);

        ChatMessageListResponse result = chatMessageService.getAllChatMessages();

        assertNotNull(result);
        assertEquals(expected.size(),result.getList().size());

    }
}
