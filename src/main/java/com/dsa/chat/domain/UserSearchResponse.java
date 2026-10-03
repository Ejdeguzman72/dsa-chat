package com.dsa.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSearchResponse {

    DsaUser user;
    DsaUser authInfo;

    public DsaUser getUser() {
        return user;
    }

    public void setUser(DsaUser user) {
        this.user = user;
    }

    public DsaUser getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(DsaUser authInfo) {
        this.authInfo = authInfo;
    }
}
