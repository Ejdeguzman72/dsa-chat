package com.dsa.chat.controller;

import com.dsa.chat.domain.EventListResponse;
import com.dsa.chat.domain.EventSearchResponse;
import com.dsa.chat.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

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
    }
    @Test
    public void getAllEventsByType_successTest() {
        String eventType = "RECREATIONAL";
        EventListResponse expected = new EventListResponse();

        when(dsaEventController.getAllEventsByType(eq(eventType))).thenReturn(expected);

        EventListResponse result = dsaEventController.getAllEventsByType(eventType);

        assertNotNull(result);
        assertEquals(expected,result);
    }

    @Test
    public void getEventById_successTest() {
        UUID eventId = UUID.randomUUID();

        EventSearchResponse expected = new EventSearchResponse();

        when(dsaEventController.getEventById(eq(eventId))).thenReturn(expected);

        EventSearchResponse result = dsaEventController.getEventById(eventId);

        assertNotNull(result);
        assertEquals(expected,result);
    }
}
