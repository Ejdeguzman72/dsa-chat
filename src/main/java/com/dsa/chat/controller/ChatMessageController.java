package com.dsa.chat.controller;

import com.dsa.chat.domain.ChatMessageAddUpdateRequest;
import com.dsa.chat.domain.ChatMessageAddUpdateResponse;
import com.dsa.chat.domain.ChatMessageListResponse;
import com.dsa.chat.domain.ChatMessageSearchResponse;
import com.dsa.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;
    @GetMapping("/chat/all")
    public ChatMessageListResponse getAllChatMessages() {
        return chatMessageService.getAllChatMessages();
    }
    @GetMapping("/chat/all/user/{userId}")
    public ChatMessageListResponse getAllChatMessagesByUser(@PathVariable long userId) {
        return chatMessageService.getAllChatMessagesByUser(userId);
    }
    @GetMapping("/chat/search/chatMessageId/{chatMessageId}")
    public ChatMessageSearchResponse searchChatMessageById(@PathVariable long chatMessageId) {
        return chatMessageService.searchChatMessageById(chatMessageId);
    }
}
