package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.service.EventAttendeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class EventAttendeeController {
    @Autowired
    private EventAttendeeService eventAttendeeService;
    @ApiOperation(value = AppConstants.RETRIEVE_ATTENDEES_BY_EVENT)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(value = UriConstants.RETRIEVE_ATTENDEES_BY_EVENT)
    @CrossOrigin(origins = AppConstants.CROSS_ORIGIN_ALL_ORIGINS, maxAge = AppConstants.CROSS_ORIGIN_MAX_AGE)
    public EventAttendeeListResponse retrieveAllEventAttendeesByEvent(UUID eventId) {
        return eventAttendeeService.findAttendeesByEvent(eventId);
    }

    @ApiOperation(value = AppConstants.SAVE_ATTENDANCE)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PostMapping(value = UriConstants.SAVE_ATTENDANCE)
    @CrossOrigin(origins = AppConstants.CROSS_ORIGIN_ALL_ORIGINS, maxAge = AppConstants.CROSS_ORIGIN_MAX_AGE)
    public EventAttendeeAddUpdateResponse saveAttendance(EventAttendeeAddUpdateRequest request) {
        return eventAttendeeService.saveAttendance(request);
    }

    @ApiOperation(value = AppConstants.UPDATE_ATTENDANCE)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PostMapping(value = UriConstants.UPDATE_ATTENDANCE)
    @CrossOrigin(origins = AppConstants.CROSS_ORIGIN_ALL_ORIGINS, maxAge = AppConstants.CROSS_ORIGIN_MAX_AGE)
    public EventAttendeeAddUpdateResponse updateAttendance(EventAttendeeAddUpdateRequest request) {
        return eventAttendeeService.updateAttendance(request);
    }
}
