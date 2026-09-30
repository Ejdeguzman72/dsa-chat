package com.dsa.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListResponse {

    List<DsaUser> list;

    public List<DsaUser> getList() {
        return list;
    }

    public void setList(List<DsaUser> list) {
        this.list = list;
    }
}
