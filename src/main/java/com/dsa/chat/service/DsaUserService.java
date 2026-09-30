package com.dsa.chat.service;

import com.dsa.chat.domain.*;
import com.dsa.chat.entity.DsaUser;
import com.dsa.chat.repository.DsaUserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DsaUserService {
    @Autowired
    private DsaUserRepositoryImpl dsaUserRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;

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

    public DsaUserAddUpdateResponse updateDSAUserInfo(DsaUserAddUpdateRequest request) {
        DsaUserAddUpdateResponse response = new DsaUserAddUpdateResponse();
        int result = 0;
        if (request != null) {
            result = dsaUserRepository.updateDsaUser(request);
            if (result > 0) {
                DsaUser updatedUser = new DsaUser();
                updatedUser.setUsername(request.getUsername());
                updatedUser.setPassword(request.getPassword());
                updatedUser.setEmail(request.getEmail());
                updatedUser.setDescription(request.getDescription());
                updatedUser.setFirstname(request.getFirstname());
                updatedUser.setLastname(request.getLastname());
                updatedUser.setInterests(request.getInterests());
                updatedUser.setLastUpdatetime(LocalDate.now());

                response.setDsaUser(updatedUser);
            }
        }
        return response;
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

    public ResponseEntity<DsaUser> registerNewDsaUser(RegisterRequest request) {
        DsaUser dsaUser = new DsaUser();
        dsaUser.setUsername(request.getUsername());
        dsaUser.setPassword(passwordEncoder.encode(request.getPassword()));
        dsaUser.setEmail(request.getEmail());
        dsaUser.setFirstname(request.getFirstname());
        dsaUser.setLastname(request.getLastname());
        dsaUser.setDescription(request.getDescription());
        dsaUser.setInterests(request.getInterests());
        dsaUser.setCreationDate(LocalDate.now());
        dsaUser.setLastUpdatetime(LocalDate.now());

        dsaUserRepository.registerNewDsaUser(dsaUser);
        return ResponseEntity.ok(dsaUser);
    }
}
