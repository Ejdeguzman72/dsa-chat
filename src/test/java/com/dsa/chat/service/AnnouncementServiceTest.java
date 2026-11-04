package com.dsa.chat.service;

import com.dsa.chat.domain.*;
import com.dsa.chat.repository.AnnouncementRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnouncementServiceTest {
    @Mock
    private AnnouncementRepositoryImpl announcementRepository;
    @InjectMocks
    private AnnouncementService announcementService;

    @Test
    public void retrieveAllAnnouncementsTest() {
        AnnouncementListResponse expected = new AnnouncementListResponse();
        List<Announcement> list = new ArrayList<>();
        Announcement announcement = new Announcement();
        announcement.setContent("This is test content for announcement");
        announcement.setSentDatetime(LocalDateTime.now());
        announcement.setCreationDate(LocalDateTime.now());
        announcement.setUpdateDateTime(LocalDateTime.now());
        list.add(announcement);
        expected.setList(list);

        when(announcementRepository.retrieveAllAnnouncements()).thenReturn(expected.getList());

        AnnouncementListResponse result = announcementService.getAllAnnouncements();

        assertEquals(expected.getList(),result.getList());
    }

    @Test
    public void getAnnouncementById_successTest() {
        AnnouncementSearchResponse expected = new AnnouncementSearchResponse();
        Announcement announcement = new Announcement();
        announcement.setAnnouncementId(1000L);
        announcement.setContent("This is test content for announcement");
        announcement.setSentDatetime(LocalDateTime.now());
        announcement.setCreationDate(LocalDateTime.now());
        announcement.setUpdateDateTime(LocalDateTime.now());

        expected.setAnnouncement(announcement);

        when(announcementRepository.retrieveAnnouncement(anyLong())).thenReturn(announcement);

        AnnouncementSearchResponse result = announcementService.getAnnouncementById(1000L);

        assertEquals(expected.getAnnouncement(),result.getAnnouncement());
    }

    @Test
    public void createAnnouncement_successTest() {
        AnnouncementAddUpdateRequest request = new AnnouncementAddUpdateRequest();
        request.setContent("This is test content for request");
        request.setCreationDate(LocalDateTime.now());
        request.setSentDatetime(LocalDateTime.now());
        request.setUpdateDateTime(LocalDateTime.now());

        AnnouncementAddUpdateResponse expected = new AnnouncementAddUpdateResponse();
        Announcement announcement = new Announcement();
        announcement.setContent(request.getContent());
        announcement.setSentDatetime(request.getSentDatetime());
        announcement.setCreationDate(request.getCreationDate());
        announcement.setUpdateDateTime(request.getUpdateDateTime());
        expected.setAnnouncement(announcement);

        when(announcementRepository.createAnnouncement(any(Announcement.class))).thenReturn(1);

        AnnouncementAddUpdateResponse result = announcementService.createAnnouncement(request);

        assertEquals(expected.getAnnouncement().getContent(),result.getAnnouncement().getContent());
    }

    @Test
    public void updateAnnouncement_successTest() {
        AnnouncementAddUpdateRequest request = new AnnouncementAddUpdateRequest();
        request.setAnnouncementId(5000L);
        request.setContent("This is test content for request");
        request.setCreationDate(LocalDateTime.now());
        request.setSentDatetime(LocalDateTime.now());
        request.setUpdateDateTime(LocalDateTime.now());

        AnnouncementAddUpdateResponse expected = new AnnouncementAddUpdateResponse();
        Announcement announcement = new Announcement();
        announcement.setContent(request.getContent());
        announcement.setSentDatetime(request.getSentDatetime());
        announcement.setCreationDate(request.getCreationDate());
        announcement.setUpdateDateTime(request.getUpdateDateTime());
        expected.setAnnouncement(announcement);

        when(announcementRepository.updateAnnouncement(any(Announcement.class))).thenReturn(1);

        AnnouncementAddUpdateResponse result = announcementService.updateAnnouncement(request);

        assertEquals(expected.getAnnouncement().getContent(),result.getAnnouncement().getContent());
    }

    @Test
    public void deleteAnnouncement_successTest() {
        Announcement announcement = new Announcement();
        announcement.setAnnouncementId(5000L);
        announcement.setContent("This is test content for request");
        announcement.setCreationDate(LocalDateTime.now());
        announcement.setSentDatetime(LocalDateTime.now());
        announcement.setUpdateDateTime(LocalDateTime.now());

        AnnouncementSearchResponse expected = new AnnouncementSearchResponse();
        expected.setAnnouncement(announcement);

        when(announcementRepository.retrieveAnnouncement(5000L)).thenReturn(announcement);
        when(announcementRepository.deleteAnnouncement(5000L)).thenReturn(1);

        AnnouncementSearchResponse result = announcementService.deleteAnnouncement(5000L);

        assertEquals(expected.getAnnouncement(),result.getAnnouncement());
    }
}
