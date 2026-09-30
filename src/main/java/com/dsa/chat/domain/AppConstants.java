package com.dsa.chat.domain;

public class AppConstants {
    public static final String USERS_TABLE_NAME = "dsa_user";
    public static final String ROLE_TABLE_NAME = "role";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String API_RESPONSE_OK = "OK";
    public static final String CROSS_ORIGIN_ALL_ORIGINS = "*";
    public static final long CROSS_ORIGIN_MAX_AGE = 3600;
    public static final String API_RESPONSE_INVALID = "Invalid";
    public static final String API_RESPONSE_INTERNAL_SERVER_ERROR = "Internal server error";
    public static final int API_RESPONSE_HTTP_STATUS_OK = 200;
    public static final int API_RESPONSE_HTTP_STATUS_INVALID = 400;
    public static final int API_RESPONSE_HTTP_STATUS_INTERNAL_SERVER_ERROR = 200;
    public static final String API_OPERATION_AUTHENTICATE_USERS = "AUTHENTICATE_USERS";
    public static final String API_OPERATION_REGISTER_NEW_USER = "REGISTER_NEW_USERS";
    public static final String API_OPERATION_GET_ALL_DSA_USERS = "GET_ALL_DSA_USERS";
    public static final String API_OPERATION_GET_DSA_USER_BY_ID = "GET_DSA_USER_BY_ID";
    public static final String API_OPERATION_GET_DSA_USER_BY_USERNAME = "GET_DSA_USER_BY_USERNAME";
    public static final String API_OPERATION_REGISTER_NEW_DSA_USER = "REGISTER_NEW_DSA_USER";
    public static final String API_OPERATION_UPDATE_DSA_USER_INFO = "UPDATE_DSA_USER_INFO";
    public static final String API_OPERATION_DELETE_DSA_USER = "DELETE_DSA_USER";
}
