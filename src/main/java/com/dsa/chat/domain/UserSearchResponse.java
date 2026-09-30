package com.dsa.chat.domain;

import com.dsa.chat.entity.DsaUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSearchResponse {

    DsaUser user;
    com.dsa.chat.entity.DsaUser authInfo;

    public DsaUser getUser() {
        return user;
    }

    public void setUser(DsaUser user) {
        this.user = user;
    }

    public com.dsa.chat.entity.DsaUser getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(com.dsa.chat.entity.DsaUser authInfo) {
        this.authInfo = authInfo;
    }
}
