package com.dsa.chat.repository;

import com.dsa.chat.domain.UserSearchResponse;
import com.dsa.chat.entity.DsaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DsaUserRepositoryImpl implements com.dsa.chat.repository.DsaUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<com.dsa.chat.repository.DsaUserRepository> retrieveAllUsers() {
        return null;
    }

    @Override
    public UserSearchResponse retrieveUserById(long userId) {
        return null;
    }

    @Override
    public UserSearchResponse retrieveUserByUsername(String username) {
        return null;
    }

    @Override
    public int registerNewDsaUser(DsaUser request) {
        return 0;
    }

    @Override
    public int updateDsaUser(DsaUser request) {
        return 0;
    }

    @Override
    public int deleteDsaUser(long userId) {
        return 0;
    }
}
