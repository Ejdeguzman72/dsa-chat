package com.dsa.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventAttendeeListResponse {
    List<EventAttendee> list;

    public List<EventAttendee> getList() {
        return list;
    }

    public void setList(List<EventAttendee> list) {
        this.list = list;
    }
}
