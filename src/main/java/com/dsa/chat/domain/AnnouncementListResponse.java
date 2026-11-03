package com.dsa.chat.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@CrossOrigin
public class AnnouncementListResponse {
    List<Announcement> list;

    public List<Announcement> getList() {
        return list;
    }

    public void setList(List<Announcement> list) {
        this.list = list;
    }
}
