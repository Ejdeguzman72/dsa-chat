package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;
    @GetMapping(UriConstants.GET_ALL_MESSAGES)
    public ChatMessageListResponse getAllChatMessages() {
        return chatMessageService.getAllChatMessages();
    }
    @GetMapping(UriConstants.GET_MESSAGES_BY_USER)
    public ChatMessageListResponse getAllChatMessagesByUser(@PathVariable long userId) {
        return chatMessageService.getAllChatMessagesByUser(userId);
    }
    @GetMapping(UriConstants.GET_MESSAGE_BY_ID)
    public ChatMessageSearchResponse searchChatMessageById(@PathVariable long chatMessageId) {
        return chatMessageService.searchChatMessageById(chatMessageId);
    }
    @PostMapping(UriConstants.SAVE_MESSAGE_INFO)
    public ChatMessageAddUpdateResponse saveMessage(@RequestBody ChatMessageAddUpdateRequest request) {
        return chatMessageService.sendMessage(request);
    }
    @PutMapping(UriConstants.UPDATE_MESSAGE_INFO)
    public ChatMessageAddUpdateResponse updateMessage(@RequestBody ChatMessageAddUpdateRequest request) {
        return chatMessageService.updateMessage(request);
    }
    @DeleteMapping(UriConstants.DELETE_MESSAGE)
    public ChatMessageSearchResponse deleteMessage(@PathVariable long chatMessageId) {
        return chatMessageService.deleteMessage(chatMessageId);
    }
}
