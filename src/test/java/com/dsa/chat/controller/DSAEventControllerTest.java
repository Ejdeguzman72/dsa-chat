package com.dsa.chat.controller;

import com.dsa.chat.domain.EventListResponse;
import com.dsa.chat.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}
