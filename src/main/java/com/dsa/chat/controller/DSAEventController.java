package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class DSAEventController {
    @Autowired
    private EventService eventService;
    @GetMapping(UriConstants.GET_ALL_EVENTS)
    public EventListResponse getAllEvents() {
        return eventService.getAllEvents();
    }
    @GetMapping(UriConstants.GET_ALL_EVENTS)
    public EventListResponse getAllEventsByType(@PathVariable String eventType) {
        return eventService.getAllEventsByType(eventType);
    }
    @GetMapping(UriConstants.GET_EVENT_BY_ID)
    public EventSearchResponse getEventById(@PathVariable UUID eventId) {
        return eventService.getEventById(eventId);
    }
    @GetMapping(UriConstants.GET_EVENT_BY_NAME)
    public EventSearchResponse getEventByName(@PathVariable String eventName) { return eventService.getEventByName(eventName); }
    @PostMapping(UriConstants.ADD_EVENT_INFO)
    public EventAddUpdateResponse addEventInfo(@RequestBody EventAddUpdateRequest request) { return eventService.saveMessageInfo(request); }
    @PutMapping(UriConstants.UPDATE_EVENT_INFO)
    public EventAddUpdateResponse updateEventInfo(@RequestBody EventAddUpdateRequest request) { return eventService.updateEventInfo(request); }
    @DeleteMapping(UriConstants.DELETE_MESSAGE)
    public EventSearchResponse deleteEventInfo(@PathVariable UUID eventId) { return eventService.deleteEventInfo(eventId); }
}
