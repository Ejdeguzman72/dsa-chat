package com.dsa.chat.service;

import com.dsa.chat.domain.RegisterRequest;
import com.dsa.chat.domain.UserListResponse;
import com.dsa.chat.domain.UserSearchResponse;
import com.dsa.chat.entity.DsaUser;
import com.dsa.chat.repository.DsaUserDaoImpl;
import com.dsa.chat.repository.F2DUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class F2DUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(F2DUserService.class);

    @Autowired
    private DsaUserDaoImpl dsaUserDao;

    public UserListResponse retrieveAllUsers() {
        UserListResponse response = new UserListResponse();

        return response;
    }

    public UserSearchResponse retrieveUserById(long userId) {
        UserSearchResponse response = new UserSearchResponse();

        return response;
    }

    public UserSearchResponse retrieveUserByUsername(String username) {
        UserSearchResponse response = new UserSearchResponse();

        return response;
    }

    public ResponseEntity<DsaUser> registerNewF2DUser(RegisterRequest request) {


    }

    public UserSearchResponse deleteUserById(long userId) {
        UserSearchResponse response = new UserSearchResponse();

        return response;
    }
}
