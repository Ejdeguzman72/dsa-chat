package com.dsa.chat.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.CrossOrigin;

@JsonInclude(JsonInclude.Include.NON_NULL)
@CrossOrigin
public class AnnouncementAddUpdateRequest extends Announcement {
}
