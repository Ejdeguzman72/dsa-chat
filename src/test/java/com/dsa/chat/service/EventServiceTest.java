package com.dsa.chat.service;

import com.dsa.chat.domain.DSAEvent;
import com.dsa.chat.domain.EventListResponse;
import com.dsa.chat.domain.EventSearchResponse;
import com.dsa.chat.repository.EventRepositoryImpl;
import jdk.jfr.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepositoryImpl eventRepository;
    @InjectMocks
    private EventService eventService;
    @BeforeEach
    void setUpTest() {

    }
    @Test
    public void getAllEvents_successTest() {
        DSAEvent event = new DSAEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventName("TEST EVENT NAME");
        event.setDescription("TEST DESCRIPTION FOR TEST EVENT");
        event.setEventDate(LocalDate.now());
        event.setEventType("TEST EVENT TYPE");
        event.setCreateTime(LocalDate.now());
        event.setLastUpdateTime(LocalDate.now());
        event.setAttendees(null);
        event.setConfirmedAttendees(null);
        event.setTentativeAttendees(null);
        event.setDeclinedAttendees(null);
        List<DSAEvent> expected = List.of(event);

        when(eventRepository.getAllEvents()).thenReturn(expected);

        EventListResponse result = eventService.getAllEvents();

        assertNotNull(result);
        assertEquals(expected,result.getList());
    }

    @Test
    public void getAllEventsByType_successTest() {
        DSAEvent event = new DSAEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventName("TEST EVENT NAME");
        event.setDescription("TEST DESCRIPTION FOR TEST EVENT");
        event.setEventDate(LocalDate.now());
        event.setEventType("TEST EVENT TYPE");
        event.setCreateTime(LocalDate.now());
        event.setLastUpdateTime(LocalDate.now());
        event.setAttendees(null);
        event.setConfirmedAttendees(null);
        event.setTentativeAttendees(null);
        event.setDeclinedAttendees(null);
        List<DSAEvent> expected = List.of(event);

        when(eventRepository.getAllEvents()).thenReturn(expected);

        EventListResponse result = eventService.getAllEvents();

        assertNotNull(result);
        assertEquals(expected,result.getList());
    }

    @Test
    public void getEventById_successTest() {
        DSAEvent event = new DSAEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventName("TEST EVENT NAME");
        event.setDescription("TEST DESCRIPTION FOR TEST EVENT");
        event.setEventDate(LocalDate.now());
        event.setEventType("TEST EVENT TYPE");
        event.setCreateTime(LocalDate.now());
        event.setLastUpdateTime(LocalDate.now());
        event.setAttendees(null);
        event.setConfirmedAttendees(null);
        event.setTentativeAttendees(null);
        event.setDeclinedAttendees(null);

        UUID eventId = UUID.randomUUID();

        when(eventRepository.searchDsaEventById(eq(eventId))).thenReturn(event);

        EventSearchResponse result = eventService.getEventById(eventId);

        assertNotNull(result);
        assertEquals(event,result.getEvent());
    }

    @Test
    public void getEventByName_successTest() {
        DSAEvent event = new DSAEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventName("TEST EVENT NAME");
        event.setDescription("TEST DESCRIPTION FOR TEST EVENT");
        event.setEventDate(LocalDate.now());
        event.setEventType("TEST EVENT TYPE");
        event.setCreateTime(LocalDate.now());
        event.setLastUpdateTime(LocalDate.now());
        event.setAttendees(null);
        event.setConfirmedAttendees(null);
        event.setTentativeAttendees(null);
        event.setDeclinedAttendees(null);

        when(eventRepository.searchDsaEventByName(eq("TEST EVENT NAME"))).thenReturn(event);

        EventSearchResponse result = eventService.getEventByName("TEST EVENT NAME");

        assertNotNull(result);
        assertEquals(event,result.getEvent());
    }


}
