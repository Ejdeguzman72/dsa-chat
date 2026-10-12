package com.dsa.chat.service;

import com.dsa.chat.domain.DsaUser;
import com.dsa.chat.repository.DsaUserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DsaUserDetailsServiceTest {
    @Mock
    private DsaUserRepositoryImpl dsaUserRepository;
    @InjectMocks
    private DSAUserDetailsService dsaUserDetailsService;

    @Test
    public void loadUserByUsername_success_test() {
        DsaUser expected = new DsaUser();
        expected.setUsername("TEST USERNAME");
        expected.setPassword("TEST PASSWORD");
        expected.setFirstname("FIRSTNAME");
        expected.setLastname("LASTNAME");
        expected.setDescription("THIS IS A TEST DESCRIPTION");
        expected.setInterests(null);
        expected.setEmail("123@gmail.com");

        expected.setUserId(1000);
        expected.setCreationDate(LocalDate.now());
        expected.setLastUpdatetime(LocalDate.now());

        when(dsaUserRepository.retrieveUserByUsername("TEST USERNAME")).thenReturn(expected);

        UserDetails result = dsaUserDetailsService.loadUserByUsername("TEST USERNAME");

        assertNotNull(result);
        assertEquals(expected.getUsername(),result.getUsername());
        assertEquals(expected.getPassword(),result.getPassword());
    }
}
