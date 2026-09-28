package com.dsa.chat.service;

import com.dsa.chat.domain.RegisterRequest;
import com.dsa.chat.domain.UserListResponse;
import com.dsa.chat.domain.UserSearchResponse;
import com.dsa.chat.entity.DsaUser;
import com.dsa.chat.repository.DsaUserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DsaUserService {
    @Autowired
    private DsaUserRepositoryImpl dsaUserRepository;

    public UserListResponse retrieveAllUsers() {
        UserListResponse response = new UserListResponse();
        List<DsaUser> list = dsaUserRepository.retrieveAllUsers();
        response.setList(list);
        return response;
    }

    public UserSearchResponse retrieveUserById(long userId) {
        UserSearchResponse response = new UserSearchResponse();
        DsaUser dsaUser = dsaUserRepository.retrieveUserById(userId);
        response.setUser(dsaUser);
        return response;
    }

    public UserSearchResponse retrieveUserByUsername(String username) {
        UserSearchResponse response = new UserSearchResponse();
        DsaUser dsaUser = dsaUserRepository.retrieveUserByUsername(username);
        response.setUser(dsaUser);
        return response;
    }

    public ResponseEntity<DsaUser> registerNewF2DUser(RegisterRequest request) {
        return ResponseEntity.ok(null);
    }

    public UserSearchResponse deleteUserById(long userId) {
        UserSearchResponse response = new UserSearchResponse();
        DsaUser dsaUser = dsaUserRepository.retrieveUserById(userId);
        if (dsaUser != null) {
            dsaUserRepository.deleteDsaUser(userId);
        }

        response.setUser(dsaUser);
        return response;
    }
}
