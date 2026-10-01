package com.dsa.chat.repository;

import com.dsa.chat.domain.ChatMessageSearchResponse;
import com.dsa.chat.entity.ChatMessage;

import java.util.List;

public interface ChatMessageRepository {
    List<ChatMessage> retrieveAllMessages();
    List<ChatMessage> retrieveAllMessagesByUserId(long userId);
    ChatMessage retrieveChatMessageById(long chatMessageId);
    int addNewMessage(ChatMessage request);
    int updateMessage(ChatMessage request);
    int deleteChatMessage(long chatMessageId);
}
