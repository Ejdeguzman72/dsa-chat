package com.dsa.chat.service;

import com.dsa.chat.domain.*;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    public void getAllChatMessagesByUser() {
        ChatMessage data = testData;
        List<ChatMessage> expected = List.of(data);

        when(chatMessageRepository.retrieveAllMessagesByUserId(eq(100000L))).thenReturn(expected);

        ChatMessageListResponse result = chatMessageService.getAllChatMessagesByUser(100000);

        assertNotNull(result);
        assertEquals(expected,result.getList());
    }

    @Test
    public void searchChatMessageById_successTest() {
        ChatMessage expected = testData;

        when(chatMessageRepository.retrieveChatMessageById(eq(100000L))).thenReturn(expected);

        ChatMessageSearchResponse result = chatMessageService.searchChatMessageById(100000L);

        assertNotNull(result);
        assertEquals(expected,result.getChatMessage());
    }

    @Test
    public void sendMessage_successTest() {
        ChatMessageAddUpdateRequest request = new ChatMessageAddUpdateRequest();

        request.setChatMessageId(testData.getChatMessageId());
        request.setContent(testData.getContent());
        request.setDsaUser(testData.getDsaUser());
        request.setSentDatetime(testData.getSentDatetime());

        when(chatMessageRepository.addNewMessage(any(ChatMessage.class))).thenReturn(1);

        ChatMessageAddUpdateResponse result = chatMessageService.sendMessage(request);

        assertNotNull(result);
        assertEquals(testData.getContent(),result.getChatMessage().getContent());
    }

    @Test
    public void deleteMessage_successTest() {
        ChatMessage expected = testData;

        when(chatMessageRepository.retrieveChatMessageById(100000L)).thenReturn(expected);
        when(chatMessageRepository.deleteChatMessage(100000L)).thenReturn(1);

        ChatMessageSearchResponse result = chatMessageService.deleteMessage(100000L);

        assertNotNull(result);
    }
}
