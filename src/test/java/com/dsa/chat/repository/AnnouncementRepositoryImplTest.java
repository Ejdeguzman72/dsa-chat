package com.dsa.chat.repository;

import com.dsa.chat.domain.Announcement;
import com.dsa.chat.domain.AnnouncementMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnouncementRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private AnnouncementMapper announcementMapper;
    @InjectMocks
    private AnnouncementRepositoryImpl announcementRepository;

    @Test
    public void retrieveAllAnnouncementsTest() {
        Announcement announcement = new Announcement();
        announcement.setContent("This is test content");
        announcement.setSentDatetime(LocalDateTime.now());
        announcement.setCreationDate(LocalDateTime.now());
        announcement.setUpdateDateTime(LocalDateTime.now());

        List<Announcement> expected = List.of(announcement);

        when(jdbcTemplate.query(anyString(),eq(announcementMapper))).thenReturn(expected);

        List<Announcement> result = announcementRepository.retrieveAllAnnouncements();

        assertEquals(expected,result);
    }

    @Test
    public void retrieveAnnouncementTest() {
        Announcement expected = new Announcement();
        expected.setAnnouncementId(1L);
        expected.setContent("This is test content");
        expected.setSentDatetime(LocalDateTime.now());
        expected.setCreationDate(LocalDateTime.now());
        expected.setUpdateDateTime(LocalDateTime.now());

        when(jdbcTemplate.queryForObject(anyString(),eq(announcementMapper),eq(1L))).thenReturn(expected);

        Announcement result = announcementRepository.retrieveAnnouncement(1L);

        assertEquals(expected,result);
    }

    @Test
    public void createAnnouncementTest() {
        Announcement expected = new Announcement();
        expected.setContent("This is test content");
        expected.setSentDatetime(LocalDateTime.now());
        expected.setCreationDate(LocalDateTime.now());
        expected.setUpdateDateTime(LocalDateTime.now());

        when(jdbcTemplate.update(anyString(),any(Object[].class))).thenReturn(1);

        int result = announcementRepository.createAnnouncement(expected);

        assertEquals(1,result);
    }

    @Test
    public void updateAnnouncementTest() {
        Announcement expected = new Announcement();
        expected.setAnnouncementId(100L);
        expected.setContent("This is test content");
        expected.setSentDatetime(LocalDateTime.now());
        expected.setCreationDate(LocalDateTime.now());
        expected.setUpdateDateTime(LocalDateTime.now());

        when(jdbcTemplate.queryForObject(anyString(),eq(announcementMapper),eq(100L))).thenReturn(expected);
        when(jdbcTemplate.update(anyString(),any(Object[].class))).thenReturn(1);

        int result = announcementRepository.updateAnnouncement(expected);

        assertEquals(1,result);
    }

    @Test
    public void deleteAnnouncementTest() {
        long announcementId = 5000L;
        when(jdbcTemplate.update(anyString(),eq(announcementId))).thenReturn(1);
        int result = announcementRepository.deleteAnnouncement(announcementId);

        assertEquals(1,result);
    }
}
