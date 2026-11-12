package com.dsa.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DSAEvent {
    UUID eventId;
    String eventName;
    String eventType;
    String description;
    LocalDate eventDate;
    String address01;
    String address02;
    String city;
    String stateCode;
    String zipCode;
    String countryCode;
    LocalDate createTime;
    LocalDate lastUpdateTime;

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getAddress01() {
        return address01;
    }

    public void setAddress01(String address01) {
        this.address01 = address01;
    }

    public String getAddress02() {
        return address02;
    }

    public void setAddress02(String address02) {
        this.address02 = address02;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDate lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

//    public List<Long> getAttendees() {
//        return attendees;
//    }
//
//    public void setAttendees(List<Long> attendees) {
//        this.attendees = attendees;
//    }
//
//    public List<Long> getConfirmedAttendees() {
//        return confirmedAttendees;
//    }
//
//    public void setConfirmedAttendees(List<Long> confirmedAttendees) {
//        this.confirmedAttendees = confirmedAttendees;
//    }
//
//    public List<Long> getTentativeAttendees() {
//        return tentativeAttendees;
//    }
//
//    public void setTentativeAttendees(List<Long> tentativeAttendees) {
//        this.tentativeAttendees = tentativeAttendees;
//    }
//
//    public List<Long> getDeclinedAttendees() {
//        return declinedAttendees;
//    }
//
//    public void setDeclinedAttendees(List<Long> declinedAttendees) {
//        this.declinedAttendees = declinedAttendees;
//    }
}