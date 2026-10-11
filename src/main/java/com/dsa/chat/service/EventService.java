package com.dsa.chat.service;

import com.dsa.chat.domain.*;
import com.dsa.chat.repository.EventRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepositoryImpl eventRepository;

    public EventListResponse getAllEvents() {
        EventListResponse response = new EventListResponse();
        List<DSAEvent> list = eventRepository.getAllEvents();

        response.setList(list);
        return response;
    }

    public EventListResponse getAllEventsByType(String eventType) {
        EventListResponse response = new EventListResponse();
        List<DSAEvent> list = eventRepository.searchEventsByType(eventType);

        response.setList(list);
        return response;
    }

    public EventSearchResponse getEventById(UUID eventId) {
        EventSearchResponse response = new EventSearchResponse();
        DSAEvent event = eventRepository.searchDsaEventById(eventId);

        response.setEvent(event);
        return response;
    }

    public EventSearchResponse getEventByName(String eventName) {
        EventSearchResponse response = new EventSearchResponse();
        DSAEvent event = eventRepository.searchDsaEventByName(eventName);

        response.setEvent(event);
        return response;
    }

    public EventAddUpdateResponse saveMessageInfo(EventAddUpdateRequest request) {
        EventAddUpdateResponse response = new EventAddUpdateResponse();
        int result = 0;
        if (request != null) {
            DSAEvent event = new DSAEvent();
            event.setEventName(request.getEventName());
            event.setEventType(request.getEventType());
            event.setDescription(request.getDescription());
            event.setEventDate(request.getEventDate());
            event.setAttendees(request.getAttendees());
            event.setConfirmedAttendees(request.getConfirmedAttendees());
            event.setTentativeAttendees(request.getTentativeAttendees());
            event.setDeclinedAttendees(request.getDeclinedAttendees());
            event.setCreateTime(LocalDate.now());
            event.setLastUpdateTime(LocalDate.now());

            result = eventRepository.addNewEvent(event);
            if (result > 0) {
                response.setEvent(event);
                response.setSuccess(true);
            }
        }

        return response;
    }

    public EventAddUpdateResponse updateEventInfo(EventAddUpdateRequest request) {
        EventAddUpdateResponse response = new EventAddUpdateResponse();
        int result = 0;
        if (request != null) {
            DSAEvent event = new DSAEvent();
            event.setEventName(request.getEventName());
            event.setEventType(request.getEventType());
            event.setDescription(request.getDescription());
            event.setEventDate(request.getEventDate());
            event.setAttendees(request.getAttendees());
            event.setConfirmedAttendees(request.getConfirmedAttendees());
            event.setTentativeAttendees(request.getTentativeAttendees());
            event.setDeclinedAttendees(request.getDeclinedAttendees());
            event.setCreateTime(LocalDate.now());
            event.setLastUpdateTime(LocalDate.now());

            result = eventRepository.updateEvent(event);
            if (result > 0) {
                response.setEvent(event);
                response.setSuccess(true);
            }
        }

        return response;
    }

    public EventSearchResponse deleteEventInfo(UUID eventId) {
        EventSearchResponse response = new EventSearchResponse();
        if (eventId != null) {
            DSAEvent event = eventRepository.searchDsaEventById(eventId);
            eventRepository.deleteEvent(eventId);

        }
        return response;
    }
}
