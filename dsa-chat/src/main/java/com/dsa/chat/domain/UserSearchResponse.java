package com.dsa.chat.domain;

import com.dsa.chat.entity.DsaUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSearchResponse {

    DsaUser user;

    public DsaUser getUser() {
        return user;
    }

    public void setUser(DsaUser user) {
        this.user = user;
    }
}
