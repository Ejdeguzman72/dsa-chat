package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.service.AnnouncementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnouncementControllerTest {
    @Mock
    private AnnouncementService announcementService;
    @InjectMocks
    private AnnouncementController announcementController;

    @Test
    public void retrieveAllAnnouncements_successTest() {
        AnnouncementListResponse expected = new AnnouncementListResponse();

        when(announcementService.getAllAnnouncements()).thenReturn(expected);

        AnnouncementListResponse result = announcementController.retrieveAllAnnouncements();

        assertNotNull(result);
        assertEquals(expected,result);
    }

    @Test
    public void retrieveAnnouncementById_successTest() {
        AnnouncementSearchResponse expected = new AnnouncementSearchResponse();

        when(announcementService.getAnnouncementById(anyLong())).thenReturn(expected);

        AnnouncementSearchResponse result = announcementController.retrieveAnnouncementById(anyLong());

        assertNotNull(result);
        assertEquals(expected,result);
    }

    @Test
    public void createAnnouncement_successTest() {
        AnnouncementAddUpdateRequest request = new AnnouncementAddUpdateRequest();
        request.setContent("This is test content for announcement");

        AnnouncementAddUpdateResponse expected = new AnnouncementAddUpdateResponse();
        Announcement announcement = new Announcement();
        announcement.setContent("This is test content for announcement");
        expected.setAnnouncement(announcement);

        when(announcementService.createAnnouncement(request)).thenReturn(expected);

        AnnouncementAddUpdateResponse result = announcementController.createAnnouncement(request);

        assertNotNull(result);
        assertEquals(expected,result);
    }

    @Test
    public void updateAnnouncement_successTest() {
        AnnouncementAddUpdateRequest request = new AnnouncementAddUpdateRequest();
        request.setAnnouncementId(1000L);
        request.setContent("This is test content for announcement");

        AnnouncementAddUpdateResponse expected = new AnnouncementAddUpdateResponse();
        Announcement announcement = new Announcement();
        announcement.setAnnouncementId(1000L);
        announcement.setContent("This is test content for announcement");
        expected.setAnnouncement(announcement);

        when(announcementService.updateAnnouncement(request)).thenReturn(expected);

        AnnouncementAddUpdateResponse result = announcementController.updateAnnouncement(request);

        assertNotNull(result);
        assertEquals(expected,result);
    }

    @Test
    public void deleteAnnouncement_successTest() {
        AnnouncementSearchResponse expected = new AnnouncementSearchResponse();
        Announcement announcement = new Announcement();
        announcement.setAnnouncementId(1000L);
        announcement.setContent("This is test content for the delete test");
        announcement.setCreationDate(LocalDateTime.now());
        announcement.setSentDatetime(LocalDateTime.now());
        announcement.setUpdateDateTime(LocalDateTime.now());

        expected.setAnnouncement(announcement);

        when(announcementService.deleteAnnouncement(1000L)).thenReturn(expected);

        AnnouncementSearchResponse result = announcementController.deleteAnnouncement(1000L);

        assertNotNull(result);
        assertEquals(expected,result);
    }
}
