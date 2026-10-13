package com.dsa.chat.controller;

import com.dsa.chat.domain.ChatMessageListResponse;
import com.dsa.chat.service.ChatMessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
    }
}
