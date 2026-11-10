package com.dsa.chat.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@CrossOrigin
public class ChatMessage {
    long chatMessageId;
    String content;
    LocalDateTime sentDatetime;
    long userId;
    LocalDateTime createTime;
    LocalDateTime lastUpdateTime;
    public long getChatMessageId() {
        return chatMessageId;
    }
    public void setChatMessageId(long chatMessageId) {
        this.chatMessageId = chatMessageId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getSentDatetime() {
        return sentDatetime;
    }
    public void setSentDatetime(LocalDateTime sentDatetime) {
        this.sentDatetime = sentDatetime;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }
    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}