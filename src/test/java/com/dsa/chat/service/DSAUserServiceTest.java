package com.dsa.chat.service;

import com.dsa.chat.domain.*;
import com.dsa.chat.repository.DsaUserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DSAUserServiceTest {
    @Mock
    private DsaUserRepositoryImpl dsaUserRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private DsaUserService dsaUserService;

    private DsaUser testUserData;

    @BeforeEach
    void setUpTest() {
        testUserData = new DsaUser();
        testUserData.setUsername("testuser");
        testUserData.setPassword("password123");
        testUserData.setEmail("test@gmail.com");
        testUserData.setFirstname("John");
        testUserData.setLastname("Doe");
        testUserData.setDescription("Test description");
    }

    @Test
    public void getAllUsers_nonNullList() {
        List<DsaUser> expected = List.of(testUserData);

        when(dsaUserRepository.retrieveAllUsers()).thenReturn(expected);

        UserListResponse response = dsaUserService.retrieveAllUsers();

        assertNotNull(response);
        assertEquals(expected.size(),response.getList().size());
    }

    @Test
    public void retrieveUserById_returnUser() {
        DsaUser expected = testUserData;
        when(dsaUserRepository.retrieveUserById(1000)).thenReturn(expected);

        UserSearchResponse result = dsaUserService.retrieveUserById(1000);

        assertNotNull(result);
        assertEquals(expected,result.getUser());
    }

    @Test
    public void retrieveUserByUsername_successTest() {
        DsaUser expected = testUserData;

        when(dsaUserRepository.retrieveUserByUsername(anyString())).thenReturn(expected);

        UserSearchResponse result = dsaUserService.retrieveUserByUsername(anyString());

        assertNotNull(result);
        assertEquals(expected,result.getAuthInfo());
    }

    @Test
    public void registerDSAUserInfo_successTest() {
        DsaUser expected = testUserData;

        when(dsaUserRepository.registerNewDsaUser(expected)).thenReturn(1);

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(expected.getUsername());
        registerRequest.setPassword(expected.getPassword());
        registerRequest.setDescription(expected.getDescription());
        registerRequest.setEmail(expected.getEmail());
        registerRequest.setFirstname(expected.getFirstname());
        registerRequest.setLastname(expected.getLastname());

        ResponseEntity<DsaUser> result = dsaUserService.registerNewDsaUser(registerRequest);

        assertEquals(1,result);
    }

    @Test
    public void updateDSAUserInfo_successTest() {
        DsaUser expected = testUserData;

        when(dsaUserRepository.updateDsaUser(expected)).thenReturn(1);

        DsaUserAddUpdateRequest registerRequest = new DsaUserAddUpdateRequest();
        registerRequest.setUsername(expected.getUsername());
        registerRequest.setPassword(expected.getPassword());
        registerRequest.setDescription(expected.getDescription());
        registerRequest.setEmail(expected.getEmail());
        registerRequest.setFirstname(expected.getFirstname());
        registerRequest.setLastname(expected.getLastname());

        DsaUserAddUpdateResponse result = dsaUserService.updateDSAUserInfo(registerRequest);

        assertEquals(1,result);
    }

    @Test
    public void deleteUserByIdTest() {
        DsaUser user = testUserData;

        when(dsaUserRepository.deleteDsaUser(anyLong())).thenReturn(1);

        UserSearchResponse result = dsaUserService.deleteUserById(anyLong());

        assertNotNull(result);
        assertEquals(user,result.getUser());
    }
}
