package com.dsa.chat.service;

import com.dsa.chat.domain.*;
import com.dsa.chat.repository.EventRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        assertEquals(expected, result.getList());
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
        assertEquals(expected, result.getList());
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
        assertEquals(event, result.getEvent());
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
        assertEquals(event, result.getEvent());
    }


    @Test
    public void saveMessageInfo_successTest() {

        UUID eventId = UUID.randomUUID();
        LocalDate eventDate = LocalDate.now();

        EventAddUpdateRequest request = new EventAddUpdateRequest();
        request.setEventId(eventId);
        request.setEventName("TEST EVENT NAME");
        request.setDescription("TEST DESCRIPTION FOR TEST EVENT");
        request.setEventDate(eventDate);
        request.setEventType("TEST EVENT TYPE");

        when(eventRepository.addNewEvent(any(DSAEvent.class)))
                .thenReturn(1);

        EventAddUpdateResponse result =
                eventService.saveMessageInfo(request);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertNotNull(result.getEvent());

        assertEquals("TEST EVENT NAME", result.getEvent().getEventName());
        assertEquals(
                "TEST DESCRIPTION FOR TEST EVENT",
                result.getEvent().getDescription()
        );
        assertEquals(eventDate, result.getEvent().getEventDate());
        assertEquals("TEST EVENT TYPE", result.getEvent().getEventType());
    }

    @Test
    public void updateMessageInfo_successTest() {

        UUID eventId = UUID.randomUUID();
        LocalDate eventDate = LocalDate.now();

        EventAddUpdateRequest request = new EventAddUpdateRequest();
        request.setEventId(eventId);
        request.setEventName("TEST EVENT NAME");
        request.setDescription("TEST DESCRIPTION FOR TEST EVENT");
        request.setEventDate(eventDate);
        request.setEventType("TEST EVENT TYPE");

        when(eventRepository.addNewEvent(any(DSAEvent.class)))
                .thenReturn(1);

        EventAddUpdateResponse result =
                eventService.saveMessageInfo(request);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertNotNull(result.getEvent());

        assertEquals("TEST EVENT NAME", result.getEvent().getEventName());
        assertEquals(
                "TEST DESCRIPTION FOR TEST EVENT",
                result.getEvent().getDescription()
        );
        assertEquals(eventDate, result.getEvent().getEventDate());
        assertEquals("TEST EVENT TYPE", result.getEvent().getEventType());
    }

    @Test
    public void deleteEventInfo_successTest() {
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

        when(eventRepository.searchDsaEventById(eq(event.getEventId()))).thenReturn(event);
        when(eventRepository.deleteEvent(eq(event.getEventId()))).thenReturn(1);

        EventSearchResponse result = eventService.deleteEventInfo(event.getEventId());

        assertNotNull(result);
    }
}
