package com.dsa.chat.service;

import com.dsa.chat.domain.*;
import com.dsa.chat.repository.AnnouncementRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepositoryImpl announcementRepository;

    public AnnouncementListResponse getAllAnnouncements() {
        AnnouncementListResponse response = new AnnouncementListResponse();
        List<Announcement> list = announcementRepository.retrieveAllAnnouncements();

        response.setList(list);
        return response;
    }

    public AnnouncementSearchResponse getAnnouncementById(long announcementId) {
        AnnouncementSearchResponse response = new AnnouncementSearchResponse();
        Announcement announcement = announcementRepository.retrieveAnnouncement(announcementId);

        response.setAnnouncement(announcement);
        return response;
    }

    public AnnouncementAddUpdateResponse createAnnouncement(AnnouncementAddUpdateRequest request) {
        AnnouncementAddUpdateResponse response = new AnnouncementAddUpdateResponse();
        int result = 0;
        if (request != null) {
            Announcement announcement = new Announcement();
            announcement.setContent(request.getContent());
            announcement.setSentDatetime(LocalDateTime.now());
            announcement.setCreationDate(LocalDateTime.now());
            announcement.setUpdateDateTime(LocalDateTime.now());
            result = announcementRepository.createAnnouncement(announcement);
            if (result > 0) {
                response.setAnnouncement(announcement);
            }

        }

        return response;
    }

    public AnnouncementAddUpdateResponse updateAnnouncement(Announcement request) {
        AnnouncementAddUpdateResponse response = new AnnouncementAddUpdateResponse();
        int result = 0;
        if (request != null) {
            Announcement announcement = new Announcement();
            announcement.setContent(request.getContent());
            announcement.setSentDatetime(LocalDateTime.now());
            announcement.setCreationDate(LocalDateTime.now());
            announcement.setUpdateDateTime(LocalDateTime.now());
            result = announcementRepository.updateAnnouncement(announcement);
            if (result > 0) {
                response.setAnnouncement(announcement);
            }

        }

        return response;
    }

    public AnnouncementSearchResponse deleteAnnouncement(long announcementId) {
        AnnouncementSearchResponse response = new AnnouncementSearchResponse();
        Announcement announcement = announcementRepository.retrieveAnnouncement(announcementId);
        if (announcement != null) {
            response.setAnnouncement(announcement);
            announcementRepository.deleteAnnouncement(announcementId);
        }

        return response;
    }
}
