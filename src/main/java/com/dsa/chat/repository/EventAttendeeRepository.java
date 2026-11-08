package com.dsa.chat.repository;

import com.dsa.chat.domain.EventAttendee;

import java.util.List;
import java.util.UUID;

public interface EventAttendeeRepository {
    List<EventAttendee> findAttendeesByEvent(UUID eventId);

}
