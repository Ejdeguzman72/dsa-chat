package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.service.ChatMessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @ApiOperation(value = AppConstants.GET_ALL_MESSAGES)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.GET_ALL_MESSAGES)
    @CrossOrigin(origins = "*")
    public ChatMessageListResponse getAllChatMessages() {
        return chatMessageService.getAllChatMessages();
    }

    @ApiOperation(value = AppConstants.GET_ALL_MESSAGES_BY_USER)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.GET_MESSAGES_BY_USER)
    @CrossOrigin
    public ChatMessageListResponse getAllChatMessagesByUser(@PathVariable long userId) {
        return chatMessageService.getAllChatMessagesByUser(userId);
    }

    @ApiOperation(value = AppConstants.GET_MESSAGE_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(UriConstants.GET_MESSAGE_BY_ID)
    public ChatMessageSearchResponse searchChatMessageById(@PathVariable long chatMessageId) {
        return chatMessageService.searchChatMessageById(chatMessageId);
    }

    @ApiOperation(value = AppConstants.SAVE_MESSAGE_INFO)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PostMapping(UriConstants.SAVE_MESSAGE_INFO)
    @CrossOrigin
    public ChatMessageAddUpdateResponse saveMessage(@RequestBody ChatMessageAddUpdateRequest request) {
        return chatMessageService.sendMessage(request);
    }

    @ApiOperation(value = AppConstants.UPDATE_MESSAGE_INFO)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PutMapping(UriConstants.UPDATE_MESSAGE_INFO)
    @CrossOrigin
    public ChatMessageAddUpdateResponse updateMessage(@RequestBody ChatMessageAddUpdateRequest request) {
        return chatMessageService.updateMessage(request);
    }

    @ApiOperation(value = AppConstants.DELETE_MESSAGE)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @DeleteMapping(UriConstants.DELETE_MESSAGE)
    @CrossOrigin
    public ChatMessageSearchResponse deleteMessage(@PathVariable long chatMessageId) {
        return chatMessageService.deleteMessage(chatMessageId);
    }
}
