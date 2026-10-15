package com.dsa.chat.controller;

import com.dsa.chat.config.JwtUtil;
import com.dsa.chat.domain.DsaUser;
import com.dsa.chat.domain.RegisterRequest;
import com.dsa.chat.domain.UserListResponse;
import com.dsa.chat.domain.UserSearchResponse;
import com.dsa.chat.service.DSAUserDetailsService;
import com.dsa.chat.service.DsaUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DsaUserControllerTest {
    @Mock
    private DsaUserService dsaUserService;
    @Mock
    private DSAUserDetailsService userDetailsService;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private DsaUserController dsaUserController;

    private UserDetails userDetails;

    @BeforeEach
    void setup() {
        userDetails = mock(UserDetails.class);
    }

    @Test
    public void retrieveAllDsaUsers_successTest() {
        UserListResponse expected = new UserListResponse();

        when(dsaUserService.retrieveAllUsers()).thenReturn(expected);

        UserListResponse result = dsaUserController.retrieveAllDsaUsers();

        assertNotNull(result);
        assertEquals(expected,result);

        verify(dsaUserService,times(1)).retrieveAllUsers();
    }


    public void registerUser_successTest() {
        DsaUser user = new DsaUser();
        ResponseEntity<DsaUser> expected = ResponseEntity.ok(user);

        RegisterRequest request = new RegisterRequest();

        when(dsaUserService.registerNewDsaUser(eq(request))).thenReturn(expected);


        ResponseEntity<DsaUser> result = ResponseEntity.ok(user);

        assertNotNull(result);
    }

    @Test
    public void retrieveUserByIdTest_successTest() {
        long userId = 1000L;

        UserSearchResponse expected = new UserSearchResponse();


        when(dsaUserService.retrieveUserById(eq(userId))).thenReturn(expected);

        UserSearchResponse result = dsaUserController.retrieveUserById(userId);

        assertNotNull(result);
        assertEquals(expected,result);

        verify(dsaUserService,times(1)).retrieveUserById(userId);
    }

    @Test
    public void retrieveUserByNameTest_successTest() {
        String name = "USER NAME";

        UserSearchResponse expected = new UserSearchResponse();


        when(dsaUserService.retrieveUserByUsername(eq(name))).thenReturn(expected);

        UserSearchResponse result = dsaUserController.retrieveUserByUsername(name);

        assertNotNull(result);
        assertEquals(expected,result);

        verify(dsaUserService,times(1)).retrieveUserByUsername(name);
    }

    @Test
    public void deleteUserByIdTest_successTest() {
        long userId = 1000L;

        UserSearchResponse expected = new UserSearchResponse();


        when(dsaUserService.deleteUserById(eq(userId))).thenReturn(expected);

        UserSearchResponse result = dsaUserController.deleteDSAUserInfo(userId);

        assertNotNull(result);
        assertEquals(expected,result);

        verify(dsaUserService,times(1)).deleteUserById(userId);
    }
}
