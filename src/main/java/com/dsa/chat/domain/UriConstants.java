package com.dsa.chat.domain;

public class UriConstants {

    public static final String GET_ALL_USERS = "/users/all";
    public static final String GET_USER_BY_ID = "/users/search/id/{userId}";
    public static final String DELETE_USER_BBY_ID = "/user/delete/{userId}";
    public static final String RETRIEVE_USERNAME = "/users/user/username";
    public static final String GET_USER_BY_USERNAME = "/users/search/username/{username}";
    public static final String USER_AUTHENTICATE_URI = "/auth/authenticate";
    public static final String USER_REGISTER_URI = "/auth/register";
    public static final String AUTHORIZED_REQUESTS_PATH = "/auth/**";
    public static final String UPDATE_DSA_USER_INFO = "/users/update";

    public static final String GET_ALL_MESSAGES ="/chat/all";
    public static final String GET_MESSAGES_BY_USER="/chat/all/user/{userId}";
    public static final String GET_MESSAGE_BY_ID="/chat/search/chatMessageId/{chatMessageId}";
    public static final String SAVE_MESSAGE_INFO="/chat/save-message";
    public static final String UPDATE_MESSAGE_INFO="/chat/update-message";
    public static final String DELETE_MESSAGE="/chat/delete-message/{chatMessageId}";

    public static final String GET_ALL_EVENTS = "/events/all";
    public static final String GET_PAST_EVENTS = "/events/all/past-events";
    public static final String GET_UPCOMING_EVENTS = "/events/all/upcoming-events";
    public static final String GET_ALL_EVENTS_BY_TYPE = "/events/all/type/{eventType}";
    public static final String GET_EVENT_BY_ID = "/events/search/id/{eventId}";
    public static final String GET_EVENT_BY_NAME = "/events/search/name/{eventName}";
    public static final String ADD_EVENT_INFO = "/events/add";
    public static final String UPDATE_EVENT_INFO = "/events/update";
    public static final String DELETE_EVENT_INFO = "events/delete/{eventId}";
}
