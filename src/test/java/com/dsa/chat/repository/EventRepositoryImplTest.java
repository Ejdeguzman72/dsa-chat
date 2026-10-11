package com.dsa.chat.repository;

import com.dsa.chat.domain.DSAEvent;
import com.dsa.chat.domain.EventMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventRepositoryImplTest {
    @Mock
    JdbcTemplate jdbcTemplate;
    @Mock
    EventMapper eventMapper;
    @InjectMocks
    EventRepositoryImpl eventRepository;

    @Test
    public void getAllEventsTest() {
        DSAEvent event = new DSAEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventDate(LocalDate.now());
        event.setLastUpdateTime(LocalDate.now());
        event.setEventName("Test Event Name");
        event.setDescription("This is a test description for Test Event Name");
        event.setEventType("Test Event Type");
        event.setAttendees(null);
        event.setConfirmedAttendees(null);
        event.setTentativeAttendees(null);
        event.setDeclinedAttendees(null);
        DSAEvent event2 = new DSAEvent();
        event2.setEventId(UUID.randomUUID());
        event2.setEventDate(LocalDate.now());
        event2.setLastUpdateTime(LocalDate.now());
        event2.setEventName("Test Event 2 Name");
        event2.setDescription("This is a test description for Test Event 2 Name");
        event2.setEventType("Test Event 2 Type");
        event2.setAttendees(null);
        event2.setConfirmedAttendees(null);
        event2.setTentativeAttendees(null);
        event2.setDeclinedAttendees(null);
        List<DSAEvent> expected = List.of(event,event2);

        when(jdbcTemplate.query(anyString(),eq(eventMapper))).thenReturn(expected);

        List<DSAEvent> result = eventRepository.getAllEvents();

        assertEquals(expected,result);
    }

    @Test
    public void searchEventsByTypeTest() {
        DSAEvent event = new DSAEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventDate(LocalDate.now());
        event.setLastUpdateTime(LocalDate.now());
        event.setEventName("Test Event Name");
        event.setDescription("This is a test description for Test Event Name");
        event.setEventType("Test Event Type");
        event.setAttendees(null);
        event.setConfirmedAttendees(null);
        event.setTentativeAttendees(null);
        event.setDeclinedAttendees(null);
        DSAEvent event2 = new DSAEvent();
        event2.setEventId(UUID.randomUUID());
        event2.setEventDate(LocalDate.now());
        event2.setLastUpdateTime(LocalDate.now());
        event2.setEventName("Test Event 2 Name");
        event2.setDescription("This is a test description for Test Event 2 Name");
        event2.setEventType("Test Event 2 Type");
        event2.setAttendees(null);
        event2.setConfirmedAttendees(null);
        event2.setTentativeAttendees(null);
        event2.setDeclinedAttendees(null);
        List<DSAEvent> expected = List.of(event,event2);

        when(jdbcTemplate.query(anyString(),eq(eventMapper),eq("Test Event 2 Type"))).thenReturn(expected);

        List<DSAEvent> result = eventRepository.searchEventsByType("Test Event 2 Type");

        assertEquals(expected,result);
    }
}
