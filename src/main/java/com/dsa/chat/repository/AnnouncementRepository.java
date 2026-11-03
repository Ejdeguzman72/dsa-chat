package com.dsa.chat.repository;

import com.dsa.chat.domain.Announcement;

import java.util.List;

public interface AnnouncementRepository {
    List<Announcement> retrieveAllAnnouncements();
    Announcement retrieveAnnouncementById(long announcementId);
    int createAnnouncement(Announcement announcement);
    int updateAnnouncement(Announcement announcement);
    int deleteAnnouncement(long announcementId);
}
