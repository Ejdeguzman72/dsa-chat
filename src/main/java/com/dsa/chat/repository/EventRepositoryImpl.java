package com.dsa.chat.repository;

import com.dsa.chat.domain.DSAEvent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EventRepositoryImpl implements EventRepository {
    @Override
    public List<DSAEvent> getAllEvents() {
        return null;
    }

    @Override
    public List<DSAEvent> searchEventsByType(String eventType) {
        return null;
    }

    @Override
    public DSAEvent searchDsaEventById(UUID eventId) {
        return null;
    }

    @Override
    public DSAEvent searchDsaEventByName(String eventName) {
        return null;
    }

    @Override
    public int addNewEvent(DSAEvent dsaEvent) {
        return 0;
    }

    @Override
    public int updateEvent(DSAEvent dsaEvent) {
        return 0;
    }

    @Override
    public int deleteEvent(UUID eventId) {
        return 0;
    }
}
