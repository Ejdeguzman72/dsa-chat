package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.service.AnnouncementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    @ApiOperation(value = AppConstants.GET_ALL_ANNOUNCEMENTS)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.GET_ALL_ANNOUNCEMENTS)
    @CrossOrigin(origins = "*")
    public AnnouncementListResponse retrieveAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @ApiOperation(value = AppConstants.GET_ANNOUNCEMENT_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.GET_ANNOUNCEMENT)
    @CrossOrigin(origins = "*")
    public AnnouncementSearchResponse retrieveAnnouncementById(@PathVariable long announcementId) {
        return announcementService.getAnnouncementById(announcementId);
    }

    @ApiOperation(value = AppConstants.CREATE_ANNOUNCEMENT)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.CREATE_ANNOUNCEMENT)
    @CrossOrigin(origins = "*")
    public AnnouncementAddUpdateResponse createAnnouncement(@RequestBody AnnouncementAddUpdateRequest request) {
        return announcementService.createAnnouncement(request);
    }

    @ApiOperation(value = AppConstants.UPDATE_ANNOUNCEMENT)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.UPDATE_ANNOUNCEMENT)
    @CrossOrigin(origins = "*")
    public AnnouncementAddUpdateResponse updateAnnouncement(@RequestBody AnnouncementAddUpdateRequest request) {
        return announcementService.updateAnnouncement(request);
    }

    @ApiOperation(value = AppConstants.DELETE_ANNOUNCEMENT)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.DELETE_ANNOUNCEMENT)
    @CrossOrigin(origins = "*")
    public AnnouncementSearchResponse deleteAnnouncement(@PathVariable long announcementId) {
        return announcementService.deleteAnnouncement(announcementId);
    }
}
