package com.dsa.chat.repository;

import com.dsa.chat.domain.*;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class EventAttendeeRepositoryImpl implements EventAttendeeRepository {
    private JdbcTemplate jdbcTemplate;
    private EventAttendeeMapper eventAttendeeMapper;
    public static final String RETRIEVE_ATTENDEES_BY_EVENT = "SELECT EVENT_ID,USER_ID,ATTENDANCE_STATUS,RESPONDED_AT FROM EVENT_ATTENDEE WHERE EVENT_ID = ?";
    public static final String SAVE_ATTENDANCE = "INSERT INTO EVENT_ATTENDEE (EVENT_ID,USER_ID,ATTENDANCE_STATUS,RESPONDED_AT) " +
            "VALUES (?,?,?,?) " +
            "ON CONFLICT SET ATTENDANCE_STATUS = EXCLUDED.attendance_status,responded_at = CURRENT_TIMESTAMP";
    public static final String UPDATE_ATTENDANCE = "UPDATE EVENT_ATTENDEE SET ATTENDANCE_STATUS = ?, RESPONDED_AT = ? WHERE EVENT_ID = ? AND USER_ID = ?";
    @Autowired
    public EventAttendeeRepositoryImpl(JdbcTemplate jdbcTemplate, EventAttendeeMapper eventAttendeeMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.eventAttendeeMapper = eventAttendeeMapper;
    }
    @Override
    public List<EventAttendee> findAttendeesByEvent(UUID eventId) {
        return jdbcTemplate.query(RETRIEVE_ATTENDEES_BY_EVENT,eventAttendeeMapper,eventId);
    }

    @Override
    public int saveAttendance(EventAttendeeAddUpdateRequest request) {
        int result = 0;
        if (request != null) {
            EventAttendee eventAttendee = new EventAttendee();
            eventAttendee.setEventId(request.getEventId());
            eventAttendee.setUserId(request.getUserId());
            eventAttendee.setAttendanceStatus(request.getAttendanceStatus());
            eventAttendee.setRespondedAt(LocalDateTime.now());

            result = jdbcTemplate.update(SAVE_ATTENDANCE,new Object[] {
                    eventAttendee.getEventId(),
                    eventAttendee.getUserId(),
                    eventAttendee.getAttendanceStatus(),
                    eventAttendee.getRespondedAt()
            });
        }

        return result;
    }

    @Override
    public int updateAttendance(EventAttendeeAddUpdateRequest request) {
        int result = 0;
        if (request != null) {
            EventAttendee eventAttendee = new EventAttendee();
            eventAttendee.setEventId(request.getEventId());
            eventAttendee.setUserId(request.getUserId());
            eventAttendee.setAttendanceStatus(request.getAttendanceStatus());
            eventAttendee.setRespondedAt(LocalDateTime.now());

            result = jdbcTemplate.update(
                    UPDATE_ATTENDANCE,
                    eventAttendee.getAttendanceStatus(),
                    LocalDateTime.now(),
                    eventAttendee.getEventId(),
                    eventAttendee.getUserId());
        }

        return result;
    }
}
