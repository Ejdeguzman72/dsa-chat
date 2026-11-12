package com.dsa.chat.service;

import com.dsa.chat.domain.*;
import com.dsa.chat.repository.ChatMessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {
    @Autowired
    ChatMessageRepositoryImpl chatMessageRepository;
    public ChatMessageListResponse getAllChatMessages() {
        ChatMessageListResponse response = new ChatMessageListResponse();
        List<ChatMessage> list = chatMessageRepository.retrieveAllMessages();

        response.setList(list);
        response.setSuccess(true);
        return response;
    }
    public ChatMessageListResponse getAllChatMessagesByUser(long userId) {
        ChatMessageListResponse response = new ChatMessageListResponse();
        List<ChatMessage> list = chatMessageRepository.retrieveAllMessagesByUserId(userId);

        response.setSuccess(true);
        response.setList(list);
        return response;
    }
    public ChatMessageDTOListResponse getAllChatMessageDTOEntries() {
        ChatMessageDTOListResponse response = new ChatMessageDTOListResponse();
        List<ChatMessageDTO> list = chatMessageRepository.retrieveChatMessageDtoList();

        response.setList(list);
        return response;
    }
    public ChatMessageSearchResponse searchChatMessageById(long chatMessageId) {
        ChatMessageSearchResponse response = new ChatMessageSearchResponse();
        ChatMessage chatMessage = chatMessageRepository.retrieveChatMessageById(chatMessageId);

        response.setChatMessage(chatMessage);
        response.setSuccess(true);
        return response;
    }
    public ChatMessageAddUpdateResponse sendMessage(ChatMessageAddUpdateRequest request) {
        ChatMessageAddUpdateResponse response = new ChatMessageAddUpdateResponse();
        int result = 0;
        if (request != null) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent(request.getContent());
            chatMessage.setUserId(request.getUserId());
            chatMessage.setSentDatetime(LocalDateTime.now());
            result = chatMessageRepository.addNewMessage(chatMessage);
            if (result > 0) {
                response.setChatMessage(chatMessage);
                response.setSuccess(true);
            }
        }
        return response;
    }

    public ChatMessageAddUpdateResponse updateMessage(ChatMessageAddUpdateRequest request) {
        ChatMessageAddUpdateResponse response = new ChatMessageAddUpdateResponse();
        int result = 0;
        if (request != null) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setChatMessageId(request.getChatMessageId());
            chatMessage.setContent(request.getContent());
            chatMessage.setSentDatetime(LocalDateTime.now());
            chatMessage.setUserId(request.getUserId());
            result = chatMessageRepository.updateMessage(request);
            if (result > 0) {
                response.setChatMessage(chatMessage);
                response.setSuccess(true);
            }
        }

        return response;
    }

    public ChatMessageSearchResponse deleteMessage(long chatMessageId) {
        ChatMessageSearchResponse response = new ChatMessageSearchResponse();
        if (chatMessageId > 0) {
            ChatMessage chatMessage = chatMessageRepository.retrieveChatMessageById(chatMessageId);
            chatMessageRepository.deleteChatMessage(chatMessageId);

            response.setChatMessage(chatMessage);
        }

        return response;
    }
}
