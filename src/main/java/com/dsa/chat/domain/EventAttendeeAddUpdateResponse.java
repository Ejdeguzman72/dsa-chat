package com.dsa.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventAttendeeAddUpdateResponse {
    EventAttendee eventAttendee;

    public EventAttendee getEventAttendee() {
        return eventAttendee;
    }

    public void setEventAttendee(EventAttendee eventAttendee) {
        this.eventAttendee = eventAttendee;
    }
}
