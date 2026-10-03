package com.dsa.chat.repository;

import com.dsa.chat.domain.DSAEvent;

import java.util.List;
import java.util.UUID;

public interface EventRepository {
    List<DSAEvent> getAllEvents();
    List<DSAEvent> searchEventsByType(String eventType);
    DSAEvent searchDsaEventById(UUID eventId);
    DSAEvent searchDsaEventByName(String eventName);
    int addNewEvent(DSAEvent dsaEvent);
    int updateEvent(DSAEvent dsaEvent);
    int deleteEvent(UUID eventId);
}
