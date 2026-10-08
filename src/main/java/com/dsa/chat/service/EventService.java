package com.dsa.chat.service;

import com.dsa.chat.domain.DSAEvent;
import com.dsa.chat.domain.EventListResponse;
import com.dsa.chat.repository.EventRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
