package com.dsa.chat.repository;

import com.dsa.chat.domain.EventAddUpdateRequest;
import com.dsa.chat.domain.EventAttendee;
import com.dsa.chat.domain.EventAttendeeAddUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface EventAttendeeRepository {
    List<EventAttendee> findAttendeesByEvent(UUID eventId);
    int saveAttendance(EventAttendeeAddUpdateRequest request);
    int updateAttendance(EventAttendeeAddUpdateRequest request);
}
