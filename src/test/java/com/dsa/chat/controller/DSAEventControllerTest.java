package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DSAEventControllerTest {
    @Mock
    private EventService eventService;
    @InjectMocks
    private DSAEventController dsaEventController;

    @Test
    public void getAllEvents_successTest() {
        EventListResponse expected = new EventListResponse();

        when(dsaEventController.getAllEvents()).thenReturn(expected);

        EventListResponse result = dsaEventController.getAllEvents();

        assertNotNull(result);

        verify(eventService,times(1)).getAllEvents();
    }

    @Test
    public void getAllEventsByType_successTest() {
        String eventType = "RECREATIONAL";
        EventListResponse expected = new EventListResponse();

        when(dsaEventController.getAllEventsByType(eq(eventType))).thenReturn(expected);

        EventListResponse result = dsaEventController.getAllEventsByType(eventType);

        assertNotNull(result);
        assertEquals(expected, result);

        verify(eventService,times(1)).getAllEventsByType(eventType);
    }

    @Test
    public void getEventById_successTest() {
        UUID eventId = UUID.randomUUID();

        EventSearchResponse expected = new EventSearchResponse();
        DSAEvent event = new DSAEvent();
        event.setEventName("TEST EVENT NAME");
        expected.setEvent(event);
        when(eventService.getEventById(eq(eventId))).thenReturn(expected);

        EventSearchResponse result = dsaEventController.getEventById(eventId);

        assertNotNull(result);
        assertEquals(expected, result);

        verify(eventService,times(1)).getEventById(eventId);
    }

    @Test
    public void getEventByName_successTest() {
        String name = "TEST EVENT NAME";

        EventSearchResponse expected = new EventSearchResponse();
        DSAEvent event = new DSAEvent();
        event.setEventName("TEST EVENT NAME");
        expected.setEvent(event);
        when(eventService.getEventByName(eq(name))).thenReturn(expected);

        EventSearchResponse result = dsaEventController.getEventByName(name);


        assertNotNull(result);
        assertEquals(expected, result);
        assertEquals(expected.getEvent().getEventName(),result.getEvent().getEventName());

        verify(eventService,times(1)).getEventByName(name);
    }

    @Test
    public void addEventInfo_successTest() {
        EventAddUpdateRequest request = new EventAddUpdateRequest();
        request.setEventName("TEST EVENT NAME");

        EventAddUpdateResponse expected = new EventAddUpdateResponse();
        DSAEvent event = new DSAEvent();
        event.setEventName("TEST EVENT NAME");
        expected.setEvent(event);

        when(eventService.saveMessageInfo(eq(request))).thenReturn(expected);

        EventAddUpdateResponse result = dsaEventController.addEventInfo(request);

        assertNotNull(result);
        assertEquals(request.getEventName(),expected.getEvent().getEventName());

        verify(eventService,times(1)).saveMessageInfo(request);
    }

    @Test
    public void updateEventInfo_successTest() {
        EventAddUpdateRequest request = new EventAddUpdateRequest();
        request.setEventName("TEST EVENT NAME");

        DSAEvent event = new DSAEvent();
        event.setEventName("TEST EVENT NAME");

        EventAddUpdateResponse expected = new EventAddUpdateResponse();
        expected.setEvent(event);

        when(eventService.updateEventInfo(eq(request))).thenReturn(expected);

        EventAddUpdateResponse result = dsaEventController.updateEventInfo(request);

        assertNotNull(result);
        assertNotNull(result.getEvent());
        assertEquals(request.getEventName(), result.getEvent().getEventName());

        verify(eventService, times(1)).updateEventInfo(request);
    }

    @Test
    public void deleteEventInfo_successTest() {
        UUID eventId = UUID.randomUUID();

        EventSearchResponse expected = new EventSearchResponse();

        DSAEvent event = new DSAEvent();
        event.setEventName("TEST EVENT NAME");

        expected.setEvent(event);

        when(eventService.deleteEventInfo(eq(eventId))).thenReturn(expected);

        EventSearchResponse result = dsaEventController.deleteEventInfo(eventId);

        assertNotNull(result);
        assertEquals("TEST EVENT NAME", result.getEvent().getEventName());

        verify(eventService,times(1)).deleteEventInfo(eventId);
    }
}
