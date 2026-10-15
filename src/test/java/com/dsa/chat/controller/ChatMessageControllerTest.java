package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.service.ChatMessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatMessageControllerTest {
    @Mock
    private ChatMessageService chatMessageService;
    @InjectMocks
    private ChatMessageController chatMessageController;

    @Test
    public void getAllChatMessages_successTest() {
        ChatMessageListResponse expected = new ChatMessageListResponse();

        when(chatMessageService.getAllChatMessages()).thenReturn(expected);

        ChatMessageListResponse result = chatMessageController.getAllChatMessages();

        assertNotNull(result);
        assertEquals(expected,result);

        verify(chatMessageService,times(1)).getAllChatMessages();
    }

    @Test
    public void getAllChatMessagesByUser_successTest() {
        long userId = 20000L;
        ChatMessageListResponse expected = new ChatMessageListResponse();

        when(chatMessageService.getAllChatMessagesByUser(eq(userId))).thenReturn(expected);

        ChatMessageListResponse result = chatMessageController.getAllChatMessagesByUser(userId);

        assertNotNull(result);
        assertEquals(expected,result);

        verify(chatMessageService,times(1)).getAllChatMessagesByUser(userId);
    }

    @Test
    public void searchChatMessageById_successTest() {
        long chatMessageId = 10000L;

        ChatMessageSearchResponse expected = new ChatMessageSearchResponse();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("this is test content");
        expected.setChatMessage(chatMessage);

        when(chatMessageService.searchChatMessageById(eq(chatMessageId))).thenReturn(expected);

        ChatMessageSearchResponse result = chatMessageController.searchChatMessageById(chatMessageId);

        assertNotNull(result);
        assertEquals(expected,result);

        verify(chatMessageService,times(1)).searchChatMessageById(chatMessageId);
    }

    @Test
    public void saveMessage_successTest() {
        ChatMessageAddUpdateRequest request = new ChatMessageAddUpdateRequest();
        request.setContent("This is test content. this is a test content. This is another test content");

        ChatMessageAddUpdateResponse expected = new ChatMessageAddUpdateResponse();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("This is a test message with test content");
        expected.setChatMessage(chatMessage);

        when(chatMessageService.sendMessage(request)).thenReturn(expected);

        ChatMessageAddUpdateResponse result = chatMessageController.saveMessage(request);

        assertNotNull(result);
        assertEquals(expected,result);

        verify(chatMessageService,times(1)).sendMessage(request);
    }

    @Test
    public void updateMessage_successTest() {
        ChatMessageAddUpdateRequest request = new ChatMessageAddUpdateRequest();
        request.setContent("This is test content. this is a test content. This is another test content");

        ChatMessageAddUpdateResponse expected = new ChatMessageAddUpdateResponse();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("This is a test message with test content");
        expected.setChatMessage(chatMessage);

        when(chatMessageService.updateMessage(request)).thenReturn(expected);

        ChatMessageAddUpdateResponse result = chatMessageController.updateMessage(request);

        assertNotNull(result);
        assertEquals(expected,result);

        verify(chatMessageService,times(1)).updateMessage(request);
    }

    @Test
    public void deleteMessage_successTest() {
        long messageId = 10000L;

        ChatMessageSearchResponse expected = new ChatMessageSearchResponse();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("This is a test message with test content");
        expected.setChatMessage(chatMessage);

        when(chatMessageService.deleteMessage(eq(messageId))).thenReturn(expected);

        ChatMessageSearchResponse result = chatMessageController.deleteMessage(messageId);

        assertNotNull(result);
        assertEquals(expected,result);

        verify(chatMessageService,times(1)).deleteMessage(messageId);
    }
}
