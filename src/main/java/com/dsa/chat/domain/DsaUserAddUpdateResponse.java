package com.dsa.chat.domain;

import com.dsa.chat.entity.DsaUser;

public class DsaUserAddUpdateResponse {
    DsaUser dsaUser;

    public DsaUser getDsaUser() {
        return dsaUser;
    }

    public void setDsaUser(DsaUser dsaUser) {
        this.dsaUser = dsaUser;
    }
}
