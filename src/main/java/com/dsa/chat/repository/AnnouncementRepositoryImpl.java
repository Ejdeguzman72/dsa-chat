package com.dsa.chat.repository;

import com.dsa.chat.domain.Announcement;
import com.dsa.chat.domain.AnnouncementMapper;
import com.dsa.chat.domain.ChatMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AnnouncementRepositoryImpl {

    private JdbcTemplate jdbcTemplate;
    private AnnouncementMapper announcementMapper;
    @Autowired
    public AnnouncementRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            AnnouncementMapper announcementMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.announcementMapper = announcementMapper;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementRepositoryImpl.class);

    public static final String RETRIEVE_ALL_ANNOUNCEMENTS = "SELECT ANNOUNCEMENT_ID, CONTENT, SENT_DATETIME, CREATE_DATETIME, LAST_UPDATEDATETIME FROM ANNOUNCEMENT";
    public static final String RETRIEVE_ANNOUNCEMENT_BY_ID = "SELECT ANNOUNCEMENT_ID, CONTENT, SENT_DATETIME FROM ANNOUNCEMENT WHERE ANNOUNCEMENT_ID = ?";
    public static final String CREATE_ANNOUNCEMENT = "INSERT INTO ANNOUNCEMENT (CONTENT, SENT_DATETIME, CREATE_DATETIME, LAST_UPDATEDATETIME) VALUES (?,?,?,?)";
    public static final String UPDATE_ANNOUNCEMENT = "UPDATE ANNOUNCEMENT SET CONTENT = ? WHERE ANNOUNCEMENT_ID = ?";
    public static final String DELETE_ANNOUNCEMENT = "DELETE ANNOUNCEMENT FROM ANNOUNCEMENT WHERE ANNOUNCEMENT_ID = ?";

    public List<Announcement> retrieveAllAnnouncements() {
        return jdbcTemplate.query(RETRIEVE_ALL_ANNOUNCEMENTS,announcementMapper);
    }

    public Announcement retrieveAnnouncement(long announcementId) {
        return jdbcTemplate.queryForObject(RETRIEVE_ANNOUNCEMENT_BY_ID,announcementMapper,announcementId);
    }

    public int createAnnouncement(Announcement announcement) {
        int result = 0;
        if (announcement != null) {
            Announcement request = new Announcement();
            request.setContent(announcement.getContent());
            request.setSentDatetime(announcement.getSentDatetime());
            request.setCreationDate(announcement.getCreationDate());
            request.setUpdateDateTime(announcement.getUpdateDateTime());

            result = jdbcTemplate.update(CREATE_ANNOUNCEMENT,new Object[] {
                    request.getContent(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            });
        }

        return result;
    }

    public int updateAnnouncement(Announcement announcement) {
        int result = 0;
        Announcement existingAnnouncement = retrieveAnnouncement(announcement.getAnnouncementId());
        if (existingAnnouncement != null) {
            Announcement request = new Announcement();
            request.setContent(announcement.getContent());
            request.setSentDatetime(announcement.getSentDatetime());
            request.setCreationDate(announcement.getCreationDate());
            request.setUpdateDateTime(announcement.getUpdateDateTime());

            result = jdbcTemplate.update(UPDATE_ANNOUNCEMENT,new Object[] {
                    request
            });
        }

        return result;
    }

    public int deleteAnnouncement(long announcementId) {
        return jdbcTemplate.update(DELETE_ANNOUNCEMENT,announcementId);
    }
}
