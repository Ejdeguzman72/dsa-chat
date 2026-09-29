package com.dsa.chat.controller;

import com.dsa.chat.domain.*;
import com.dsa.chat.entity.DsaUser;
import com.dsa.chat.service.DsaUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public UserSearchResponse retrieveUserById(@PathVariable long userId) {
        return dsaUserService.retrieveUserById(userId);
    }

    @ApiOperation(value = AppConstants.API_OPERATION_GET_DSA_USER_BY_USERNAME)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @GetMapping(value = UriConstants.GET_USER_BY_USERNAME)
    @CrossOrigin(origins = AppConstants.CROSS_ORIGIN_ALL_ORIGINS, maxAge = AppConstants.CROSS_ORIGIN_MAX_AGE)
    public UserSearchResponse retrieveUserByUsername(@PathVariable String username) {
        return dsaUserService.retrieveUserByUsername(username);
    }

    @ApiOperation(value = AppConstants.API_OPERATION_UPDATE_DSA_USER_INFO)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PostMapping(value = UriConstants.UPDATE_DSA_USER_INFO)
    @CrossOrigin(origins = AppConstants.CROSS_ORIGIN_ALL_ORIGINS, maxAge = AppConstants.CROSS_ORIGIN_MAX_AGE)
    public DsaUserAddUpdateResponse updateDSAUser(@RequestBody DsaUserAddUpdateRequest request) {
        return dsaUserService.updateDSAUserInfo(request);
    }

    @ApiOperation(value = AppConstants.API_OPERATION_DELETE_DSA_USER)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PutMapping(value = UriConstants.DELETE_USER_BBY_ID)
    @CrossOrigin(origins = AppConstants.CROSS_ORIGIN_ALL_ORIGINS, maxAge = AppConstants.CROSS_ORIGIN_MAX_AGE)
    public UserSearchResponse deleteDSAUserInfo(@PathVariable long userId) {
        return dsaUserService.deleteUserById(userId);
    }
}
