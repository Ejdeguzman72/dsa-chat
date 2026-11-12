package com.dsa.chat.repository;

import com.dsa.chat.domain.Announcement;
import com.dsa.chat.domain.AnnouncementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AnnouncementRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;
    private final AnnouncementMapper announcementMapper;
    @Autowired
    public AnnouncementRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            AnnouncementMapper announcementMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.announcementMapper = announcementMapper;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementRepositoryImpl.class);

    public static final String RETRIEVE_ALL_ANNOUNCEMENTS = "SELECT ANNOUNCEMENT_ID, TITLE, CONTENT, SENT_DATETIME, CREATE_DATETIME, LAST_UPDATEDATETIME FROM ANNOUNCEMENT";
    public static final String RETRIEVE_ANNOUNCEMENT_BY_ID = "SELECT ANNOUNCEMENT_ID, TITLE, CONTENT, SENT_DATETIME, CREATE_DATETIME, LAST_UPDATEDATETIME FROM ANNOUNCEMENT WHERE ANNOUNCEMENT_ID = ?";
    public static final String CREATE_ANNOUNCEMENT = "INSERT INTO ANNOUNCEMENT (TITLE, CONTENT, SENT_DATETIME, CREATE_DATETIME, LAST_UPDATEDATETIME) VALUES (?,?,?,?,?)";
    public static final String UPDATE_ANNOUNCEMENT = "UPDATE ANNOUNCEMENT SET TITLE = ?, CONTENT = ?, LAST_UPDATEDATETIME = NOW() WHERE ANNOUNCEMENT_ID = ?";
    public static final String DELETE_ANNOUNCEMENT = "DELETE FROM ANNOUNCEMENT WHERE ANNOUNCEMENT_ID = ?";

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
            request.setTitle(announcement.getTitle());
            request.setContent(announcement.getContent());
            request.setSentDatetime(announcement.getSentDatetime());
            request.setCreationDate(announcement.getCreationDate());
            request.setUpdateDateTime(announcement.getUpdateDateTime());

            result = jdbcTemplate.update(CREATE_ANNOUNCEMENT,new Object[] {
                    request.getTitle(),
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
        System.out.println(existingAnnouncement);
        if (existingAnnouncement != null) {
            Announcement request = new Announcement();
            request.setAnnouncementId(announcement.getAnnouncementId());
            request.setTitle(announcement.getTitle());
            request.setContent(announcement.getContent());
            request.setSentDatetime(announcement.getSentDatetime());
            request.setCreationDate(announcement.getCreationDate());
            request.setUpdateDateTime(announcement.getUpdateDateTime());

            result = jdbcTemplate.update(UPDATE_ANNOUNCEMENT,new Object[] {
                    request.getTitle(),
                    request.getContent(),
                    request.getAnnouncementId()
            });
        }

        return result;
    }

    public int deleteAnnouncement(long announcementId) {
        return jdbcTemplate.update(DELETE_ANNOUNCEMENT,announcementId);
    }
}
