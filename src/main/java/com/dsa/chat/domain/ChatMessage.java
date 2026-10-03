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
    DsaUser dsaUser;
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
    public DsaUser getDsaUser() {
        return dsaUser;
    }
    public void setDsaUser(DsaUser dsaUser) {
        this.dsaUser = dsaUser;
    }
}