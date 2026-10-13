package com.dsa.chat.controller;

import com.dsa.chat.config.JwtUtil;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    }

    @Test
    public void deleteUserByIdTest_successTest() {
        long userId = 1000L;

        UserSearchResponse expected = new UserSearchResponse();


        when(dsaUserService.deleteUserById(eq(userId))).thenReturn(expected);

        UserSearchResponse result = dsaUserController.deleteDSAUserInfo(userId);

        assertNotNull(result);
        assertEquals(expected,result);
    }
}
