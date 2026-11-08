package com.dsa.chat.domain;

public class EventRsvpRequest {
    private AttendanceStatus attendanceStatus;

    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
