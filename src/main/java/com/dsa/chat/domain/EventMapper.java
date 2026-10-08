package com.dsa.chat.domain;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class EventMapper implements RowMapper<DSAEvent> {
    @Override
    public DSAEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
        DSAEvent event = new DSAEvent();
        event.setEventId(UUID.fromString(rs.getString("EVENT_ID")));
        event.setEventDate(rs.getDate("EVENT_DATE").toLocalDate());
        event.setEventName(rs.getString("EVENT_NAME"));
        event.setEventType(rs.getString("EVENT_TYPE"));
        event.setDescription(rs.getString("DESCRIPTION"));
        event.setCreateTime(rs.getDate("CREATE_TIME").toLocalDate());
        event.setLastUpdateTime(rs.getDate("LAST_UPDATETIME").toLocalDate());
        event.setAttendees(retrieveLongList(rs,"ATTENDEES"));
        event.setConfirmedAttendees(retrieveLongList(rs,"CONFIRMED_ATTENDEES"));
        event.setTentativeAttendees(retrieveLongList(rs,"TENTATIVE_ATTENDEES"));
        event.setDeclinedAttendees(retrieveLongList(rs,"DECLINED_ATTENDEES"));

        return event;
    }

    private List<Long> retrieveLongList(ResultSet rs, String column) throws SQLException {
        Array array = rs.getArray(column);
        if (array == null) {
            return new ArrayList<>();
        }

        return Arrays.asList((Long[]) array.getArray());
    }
}
