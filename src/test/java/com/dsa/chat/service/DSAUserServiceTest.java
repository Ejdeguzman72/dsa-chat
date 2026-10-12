package com.dsa.chat.service;

import com.dsa.chat.domain.*;
import com.dsa.chat.repository.DsaUserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        when(dsaUserRepository.registerNewDsaUser(any(DsaUser.class))).thenReturn(1);

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(expected.getUsername());
        registerRequest.setPassword(expected.getPassword());
        registerRequest.setDescription(expected.getDescription());
        registerRequest.setEmail(expected.getEmail());
        registerRequest.setFirstname(expected.getFirstname());
        registerRequest.setLastname(expected.getLastname());

        ResponseEntity<DsaUser> result = dsaUserService.registerNewDsaUser(registerRequest);

        assertNotNull(result);
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(registerRequest.getUsername(),result.getBody().getUsername());
        assertEquals("encodedPassword",result.getBody().getPassword());
    }

    @Test
    public void updateDSAUserInfo_successTest() {
        DsaUser expected = testUserData;

        DsaUserAddUpdateRequest registerRequest =
                new DsaUserAddUpdateRequest();

        registerRequest.setUsername(expected.getUsername());
        registerRequest.setPassword(expected.getPassword());
        registerRequest.setDescription(expected.getDescription());
        registerRequest.setEmail(expected.getEmail());
        registerRequest.setFirstname(expected.getFirstname());
        registerRequest.setLastname(expected.getLastname());
        registerRequest.setInterests(expected.getInterests());

        when(dsaUserRepository.updateDsaUser(any(DsaUserAddUpdateRequest.class)))
                .thenReturn(1);

        DsaUserAddUpdateResponse result =
                dsaUserService.updateDSAUserInfo(registerRequest);

        assertNotNull(result);
        assertNotNull(result.getDsaUser());

        assertEquals(expected.getUsername(),
                result.getDsaUser().getUsername());

        assertEquals(expected.getEmail(),
                result.getDsaUser().getEmail());

        assertEquals(expected.getFirstname(),
                result.getDsaUser().getFirstname());

        assertEquals(expected.getLastname(),
                result.getDsaUser().getLastname());

        assertEquals(LocalDate.now(),
                result.getDsaUser().getLastUpdatetime());

        verify(dsaUserRepository, times(1))
                .updateDsaUser(registerRequest);
    }

    @Test
    public void deleteUserByIdTest() {
        DsaUser user = testUserData;
        when(dsaUserRepository.retrieveUserById(1L)).thenReturn(user);
        when(dsaUserRepository.deleteDsaUser(anyLong())).thenReturn(1);

        UserSearchResponse result = dsaUserService.deleteUserById(1L);

        assertNotNull(result);
        assertEquals(user,result.getUser());
    }
}
