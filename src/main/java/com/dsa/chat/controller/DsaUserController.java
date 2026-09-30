package com.dsa.chat.controller;

import com.dsa.chat.config.JwtUtil;
import com.dsa.chat.domain.*;
import com.dsa.chat.entity.DsaUser;
import com.dsa.chat.service.DSAUserDetailsService;
import com.dsa.chat.service.DsaUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class DsaUserController {
    @Autowired
    private DsaUserService dsaUserService;
    @Autowired
    private DSAUserDetailsService dsaUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @ApiOperation(value = AppConstants.API_OPERATION_AUTHENTICATE_USERS)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PostMapping(UriConstants.USER_AUTHENTICATE_URI)
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = dsaUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    @ApiOperation(value = AppConstants.API_OPERATION_REGISTER_NEW_USER)
    @ApiResponses(value = {
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_OK, message = AppConstants.API_RESPONSE_OK),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INVALID, message = AppConstants.API_RESPONSE_INVALID),
            @ApiResponse(code = AppConstants.API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR, message = AppConstants.API_RESPONSE_INTERNAL_SERVER_ERROR)})
    @PostMapping(UriConstants.USER_REGISTER_URI)
    @CrossOrigin(origins = "*")
    public ResponseEntity<DsaUser> registerUser(@RequestBody RegisterRequest request) {
        return dsaUserService.registerNewDsaUser(request);
    }
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
