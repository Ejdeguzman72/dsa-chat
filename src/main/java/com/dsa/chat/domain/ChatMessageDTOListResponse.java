package com.dsa.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class ChatMessageDTOListResponse {
    List<ChatMessageDTO> list;
    public List<ChatMessageDTO> getList() {
        return list;
    }
    public void setList(List<ChatMessageDTO> list) {
        this.list = list;
    }
}
