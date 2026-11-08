package com.dsa.chat.domain;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class EventAttendeeMapper implements RowMapper<EventAttendee> {
    @Override
    public EventAttendee mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventAttendee eventAttendee = new EventAttendee();
        eventAttendee.setEventId(rs.getObject("EVENT_ID",UUID.class));
        eventAttendee.setUserId(rs.getLong("USER_ID"));
        eventAttendee.setAttendanceStatus(rs.getString("ATTENDANCE_STATUS"));
        eventAttendee.setRespondedAt(rs.getObject("RESPONDED_AT", LocalDateTime.class));

        return eventAttendee;
    }
}
