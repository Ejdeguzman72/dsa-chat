package com.dsa.chat.controller;

import com.dsa.chat.domain.EventListResponse;
import com.dsa.chat.domain.UriConstants;
import com.dsa.chat.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DSAEventController {
    @Autowired
    private EventService eventService;
    @GetMapping(UriConstants.GET_ALL_EVENTS)
    public EventListResponse getAllEvents() {
        return eventService.getAllEvents();
    }
}
