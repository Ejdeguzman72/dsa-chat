package com.dsa.chat.repository;

import com.dsa.chat.domain.DsaUser;

import java.util.List;

public interface DsaUserRepository {
    List<DsaUser> retrieveAllUsers();
    DsaUser retrieveUserById(long userId);
    DsaUser retrieveUserByUsername(String username);
    int registerNewDsaUser(DsaUser request);
    int updateDsaUser(DsaUser request);
    int deleteDsaUser(long userId);
}
