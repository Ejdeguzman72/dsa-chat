package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class DSAEventController {
    @Autowired
    private EventService eventService;

    @ApiOperation(value = AppConstants.GET_ALL_EVENTS)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.GET_ALL_EVENTS)
    @CrossOrigin
    public EventListResponse getAllEvents() {
        return eventService.getAllEvents();
    }

    @ApiOperation(value = AppConstants.GET_ALL_EVENTS_BY_TYPE)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.GET_ALL_EVENTS_BY_TYPE)
    @CrossOrigin
    public EventListResponse getAllEventsByType(@PathVariable String eventType) {
        return eventService.getAllEventsByType(eventType);
    }

    @ApiOperation(value = AppConstants.GET_EVENT_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.GET_EVENT_BY_ID)
    @CrossOrigin
    public EventSearchResponse getEventById(@PathVariable UUID eventId) {
        return eventService.getEventById(eventId);
    }

    @ApiOperation(value = AppConstants.GET_EVENT_BY_NAME)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.GET_EVENT_BY_NAME)
    @CrossOrigin
    public EventSearchResponse getEventByName(@PathVariable String eventName) {
        return eventService.getEventByName(eventName);
    }

    @ApiOperation(value = AppConstants.ADD_EVENT_INFO)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PostMapping(UriConstants.ADD_EVENT_INFO)
    @CrossOrigin
    public EventAddUpdateResponse addEventInfo(@RequestBody EventAddUpdateRequest request) {
        return eventService.saveMessageInfo(request);
    }

    @ApiOperation(value = AppConstants.UPDATE_EVENT_INFO)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PutMapping(UriConstants.UPDATE_EVENT_INFO)
    @CrossOrigin
    public EventAddUpdateResponse updateEventInfo(@RequestBody EventAddUpdateRequest request) {
        return eventService.updateEventInfo(request);
    }

    @ApiOperation(value = AppConstants.DELETE_EVENT_INFO)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @DeleteMapping(UriConstants.DELETE_EVENT_INFO)
    @CrossOrigin
    public EventSearchResponse deleteEventInfo(@PathVariable UUID eventId) {
        return eventService.deleteEventInfo(eventId);
    }
}
