package com.dsa.chat.controller;

import com.dsa.chat.domain.AppConstants;
import com.dsa.chat.domain.UriConstants;
import com.dsa.chat.domain.UserListResponse;
import com.dsa.chat.service.DsaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DsaUserController {
    @Autowired
    private DsaUserService dsaUserService;

    @ApiOperation(value = AppConstants.API_OPERATION_GET_ALL_DSA_USERS)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(value = UriConstants.GET_ALL_USERS)
    @CrossOrigin(origins = AppConstants.CROSS_ORIGIN_ALL_ORIGINS, maxAge = AppConstants.CROSS_ORIGIN_MAX_AGE)
    public UserListResponse retrieveAllDsaUsers() {
        return dsaUserService.retrieveAllUsers();
    }

    @ApiOperation(value = AppConstants.API_OPERATION_GET_DSA_USER_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(value = UriConstants.GET_USER_BY_ID)
    @CrossOrigin(origins = AppConstants.CROSS_ORIGIN_ALL_ORIGINS, maxAge = AppConstants.CROSS_ORIGIN_MAX_AGE)
    public UserListResponse retrieveAllDsaUsers() {
        return dsaUserService.retrieveAllUsers();
    }
}
