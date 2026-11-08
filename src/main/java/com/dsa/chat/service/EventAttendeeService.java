package com.dsa.chat.service;

import com.dsa.chat.domain.EventAttendee;
import com.dsa.chat.domain.EventAttendeeAddUpdateRequest;
import com.dsa.chat.domain.EventAttendeeAddUpdateResponse;
import com.dsa.chat.domain.EventAttendeeListResponse;
import com.dsa.chat.repository.EventAttendeeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class EventAttendeeService {
    private final EventAttendeeRepositoryImpl eventAttendeeRepository;
    @Autowired
    public EventAttendeeService(EventAttendeeRepositoryImpl eventAttendeeRepository) {
        this.eventAttendeeRepository = eventAttendeeRepository;
    }

    public EventAttendeeListResponse findAttendeesByEvent(UUID eventId) {
        EventAttendeeListResponse response = new EventAttendeeListResponse();
        List<EventAttendee> list = eventAttendeeRepository.findAttendeesByEvent(eventId);

        response.setList(list);
        return response;
    }

    public EventAttendeeAddUpdateResponse saveAttendance(EventAttendeeAddUpdateRequest request) {
        EventAttendeeAddUpdateResponse response = new EventAttendeeAddUpdateResponse();
        int result = 0;
        if (request != null) {
            EventAttendee eventAttendee = new EventAttendee();
            eventAttendee.setEventId(request.getEventId());
            eventAttendee.setUserId(request.getUserId());
            eventAttendee.setAttendanceStatus(request.getAttendanceStatus());
            eventAttendee.setRespondedAt(LocalDateTime.now());

            result = eventAttendeeRepository.saveAttendance(request);
            if (result > 0) {
                response.setEventAttendee(eventAttendee);
            }
        }

        return response;
    }

    public EventAttendeeAddUpdateResponse updateAttendance(EventAttendeeAddUpdateRequest request) {
        EventAttendeeAddUpdateResponse response = new EventAttendeeAddUpdateResponse();
        int result = 0;
        if (request != null) {
            EventAttendee eventAttendee = new EventAttendee();
            eventAttendee.setEventId(request.getEventId());
            eventAttendee.setUserId(request.getUserId());
            eventAttendee.setAttendanceStatus(request.getAttendanceStatus());
            eventAttendee.setRespondedAt(LocalDateTime.now());

            result = eventAttendeeRepository.updateAttendance(request);
            if (result > 0) {
                response.setEventAttendee(eventAttendee);
            }
        }

        return response;
    }
}
