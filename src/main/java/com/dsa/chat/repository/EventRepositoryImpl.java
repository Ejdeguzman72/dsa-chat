package com.dsa.chat.repository;

import com.dsa.chat.domain.DSAEvent;
import com.dsa.chat.domain.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private final JdbcTemplate jdbcTemplate;
    private final EventMapper eventMapper;
    @Autowired
    public EventRepositoryImpl(JdbcTemplate jdbcTemplate,EventMapper eventMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.eventMapper = eventMapper;
    }

    public static final String GET_ALL_EVENTS = "SELECT EVENT_ID,EVENT_NAME,EVENT_TYPE,EVENT_DATE,DESCRIPTION,ATTENDEES,CONFIRMED_ATTENDEES,TENTATIVE_ATTENDEES,DECLINED_ATTENDEES,CREATE_TIME,LAST_UPDATETIME FROM DSA_EVENT";
    public static final String GET_ALL_EVENTS_BY_TYPE = "SELECT EVENT_ID,EVENT_NAME,EVENT_TYPE,EVENT_DATE,DESCRIPTION,ATTENDEES,CONFIRMED_ATTENDEES,TENTATIVE_ATTENDEES,DECLINED_ATTENDEES,CREATE_TIME,LAST_UPDATETIME FROM DSA_EVENT WHERE EVENT_TYPE = ?";
    public static final String GET_EVENT_BY_ID = "SELECT EVENT_ID,EVENT_NAME,EVENT_TYPE,EVENT_DATE,DESCRIPTION,ATTENDEES,CONFIRMED_ATTENDEES,TENTATIVE_ATTENDEES,DECLINED_ATTENDEES,CREATE_TIME,LAST_UPDATETIME FROM DSA_EVENT WHERE EVENT_ID = ?";
    public static final String GET_EVENT_BY_NAME = "SELECT EVENT_ID,EVENT_NAME,EVENT_TYPE,EVENT_DATE,DESCRIPTION,ATTENDEES,CONFIRMED_ATTENDEES,TENTATIVE_ATTENDEES,DECLINED_ATTENDEES,CREATE_TIME,LAST_UPDATETIME FROM DSA_EVENT WHERE EVENT_NAME = ?";
    public static final String ADD_NEW_EVENT = "INSERT INTO DSA_EVENT (EVENT_NAME,EVENT_TYPE,EVENT_DATE,DESCRIPTION,ATTENDEES,CONFIRMED_ATTENDEES,TENTATIVE_ATTENDEES,DECLINED_ATTENDEES,CREATE_DATETIME,LAST_UPDATETIME) VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_EXISTING_EVENT = "UPDATE SDA_EVENT SET EVENT_NAME = ?,EVENT_TYPE=?,EVENT_DATE = ?,DESCRIPTION = ?,ATTENDEES = ?, CONFIRMED_ATTENEES = ?,TENTATIVE_ATTENDEES = ?,DECLINED_ATTENDEES = ?,CREATE_DATETIME = ?,LAST_UPDATETIME = ? WHERE EVENT_ID = ?";
    public static final String DELETE_EVENT = "DELETE FROM DSA_EVENT WHERE EVENT_ID = ?";
    @Override
    public List<DSAEvent> getAllEvents() {
        List<DSAEvent> list = jdbcTemplate.query(GET_ALL_EVENTS,eventMapper);
        return list;
    }

    @Override
    public List<DSAEvent> searchEventsByType(String eventType) {
        List<DSAEvent> list = jdbcTemplate.query(GET_ALL_EVENTS_BY_TYPE,eventMapper,eventType);
        return list;
    }

    @Override
    public DSAEvent searchDsaEventById(UUID eventId) {
        try {
            DSAEvent event = jdbcTemplate.queryForObject(GET_EVENT_BY_ID,eventMapper,eventId);
            return event;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DSAEvent searchDsaEventByName(String eventName) {
        try {
            DSAEvent event = jdbcTemplate.queryForObject(GET_EVENT_BY_NAME,eventMapper,eventName);
            return event;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addNewEvent(DSAEvent dsaEvent) {
        int result = 0;
        if (dsaEvent != null) {
            result = jdbcTemplate.update(ADD_NEW_EVENT,new Object[] {
                    dsaEvent.getEventName(),
                    dsaEvent.getEventDate(),
                    dsaEvent.getEventType(),
                    dsaEvent.getDescription(),
                    dsaEvent.getAttendees(),
                    dsaEvent.getConfirmedAttendees(),
                    dsaEvent.getTentativeAttendees(),
                    dsaEvent.getDeclinedAttendees(),
                    dsaEvent.getCreateTime(),
                    dsaEvent.getLastUpdateTime()
            });
        }
        return result;
    }

    @Override
    public int updateEvent(DSAEvent request) {
        int result = 0;
        DSAEvent event = jdbcTemplate.queryForObject(GET_EVENT_BY_ID,eventMapper,request.getEventId());
        if (event != null) {
            event.setEventName(request.getEventName());
            event.setEventType(request.getEventType());
            event.setDescription(request.getDescription());
            event.setEventDate(request.getEventDate());
            event.setAttendees(request.getAttendees());
            event.setConfirmedAttendees(request.getConfirmedAttendees());
            event.setTentativeAttendees(request.getTentativeAttendees());
            event.setDeclinedAttendees(request.getDeclinedAttendees());
            event.setCreateTime(request.getCreateTime());
            event.setLastUpdateTime(LocalDate.now());
        }

        return result;
    }

    @Override
    public int deleteEvent(UUID eventId) {
        int result = 0;
        if (eventId != null) {
            result = jdbcTemplate.update(DELETE_EVENT,eventId);
        }

        return result;
    }
}
