package com.dsa.chat.repository;

import com.dsa.chat.domain.DSAUserMapper;
import com.dsa.chat.entity.DsaUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DsaUserRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private DSAUserMapper dsaUserMapper;

    @InjectMocks
    private DsaUserRepositoryImpl dsaUserRepository;


    @Test
    public void retrieveAllUsersTest() {

        // Arrange
        List<DsaUser> expectedUsers = new ArrayList<>();

        DsaUser testUser1 = new DsaUser();

        testUser1.setUserId(1000);
        testUser1.setUsername("TEST USERNAME");
        testUser1.setPassword("TEST PASSWORD");
        testUser1.setEmail("TEST EMAIL");
        testUser1.setDescription("TEST DESCRIPTION");
        testUser1.setInterests(List.of());
        testUser1.setFirstname("TEST FIRSTNAME");
        testUser1.setLastname("TEST LASTNAME");

        expectedUsers.add(testUser1);

        when(jdbcTemplate.query(
                anyString(),
                eq(dsaUserMapper)
        )).thenReturn(expectedUsers);

        // Act
        List<DsaUser> actualUsers =
                dsaUserRepository.retrieveAllUsers();

        // Assert
        assertEquals(expectedUsers, actualUsers);
        assertEquals(1, actualUsers.size());

        assertEquals(
                "TEST USERNAME",
                actualUsers.get(0).getUsername()
        );

        assertEquals(
                "TEST FIRSTNAME",
                actualUsers.get(0).getFirstname()
        );

        verify(jdbcTemplate, times(1)).query(
                anyString(),
                eq(dsaUserMapper)
        );
    }


    @Test
    public void retrieveUserByIdTest() {

        // Arrange
        DsaUser expectedDsaUser = new DsaUser();

        expectedDsaUser.setUserId(1000);
        expectedDsaUser.setUsername("TEST USERNAME");
        expectedDsaUser.setPassword("TEST PASSWORD");
        expectedDsaUser.setEmail("TEST EMAIL");
        expectedDsaUser.setDescription("TEST DESCRIPTION");
        expectedDsaUser.setInterests(List.of());
        expectedDsaUser.setFirstname("TEST FIRSTNAME");
        expectedDsaUser.setLastname("TEST LASTNAME");

        when(jdbcTemplate.queryForObject(
                anyString(),
                eq(dsaUserMapper),
                eq(1000L)
        )).thenReturn(expectedDsaUser);

        // Act
        DsaUser actualUser =
                dsaUserRepository.retrieveUserById(1000L);

        // Assert
        assertEquals(expectedDsaUser, actualUser);

        assertEquals(
                1000L,
                actualUser.getUserId()
        );

        assertEquals(
                "TEST USERNAME",
                actualUser.getUsername()
        );

        verify(jdbcTemplate, times(1))
                .queryForObject(
                        anyString(),
                        eq(dsaUserMapper),
                        eq(1000L)
                );
    }


    @Test
    public void retrieveUserByUsernameTest() {

        // Arrange
        DsaUser expectedDsaUser = new DsaUser();

        expectedDsaUser.setUserId(1000);
        expectedDsaUser.setUsername("TEST USERNAME");
        expectedDsaUser.setPassword("TEST PASSWORD");
        expectedDsaUser.setEmail("TEST EMAIL");
        expectedDsaUser.setDescription("TEST DESCRIPTION");
        expectedDsaUser.setInterests(List.of());
        expectedDsaUser.setFirstname("TEST FIRSTNAME");
        expectedDsaUser.setLastname("TEST LASTNAME");
        expectedDsaUser.setCreationDate(LocalDate.now());
        expectedDsaUser.setLastUpdatetime(LocalDate.now());

        when(jdbcTemplate.queryForObject(
                anyString(),
                eq(dsaUserMapper),
                eq("TEST USERNAME")
        )).thenReturn(expectedDsaUser);

        // Act
        DsaUser actualUser =
                dsaUserRepository.retrieveUserByUsername(
                        "TEST USERNAME"
                );

        // Assert
        assertEquals(expectedDsaUser, actualUser);

        assertEquals(
                "TEST USERNAME",
                actualUser.getUsername()
        );

        verify(jdbcTemplate, times(1))
                .queryForObject(
                        anyString(),
                        eq(dsaUserMapper),
                        eq("TEST USERNAME")
                );
    }


    @Test
    public void registerNewDsaUserTest() {

        // Arrange
        DsaUser dsaUser = new DsaUser();

        dsaUser.setUsername("TEST USERNAME");
        dsaUser.setPassword("TEST PASSWORD");
        dsaUser.setEmail("TEST EMAIL");
        dsaUser.setDescription("TEST DESCRIPTION");
        dsaUser.setInterests(List.of());
        dsaUser.setFirstname("TEST FIRSTNAME");
        dsaUser.setLastname("TEST LASTNAME");

        when(jdbcTemplate.update(
                anyString(),
                any(Object[].class)
        )).thenReturn(1);

        // Act
        int result =
                dsaUserRepository.registerNewDsaUser(dsaUser);

        // Assert
        assertEquals(1, result);

        verify(jdbcTemplate, times(1)).update(
                anyString(),
                any(Object[].class)
        );
    }


    @Test
    public void updateDsaUserTest() {

        // Arrange - existing database user
        DsaUser existingUser = new DsaUser();

        existingUser.setUserId(1000);
        existingUser.setUsername("OLD USERNAME");
        existingUser.setPassword("OLD PASSWORD");
        existingUser.setEmail("OLD EMAIL");
        existingUser.setFirstname("OLD FIRSTNAME");
        existingUser.setLastname("OLD LASTNAME");
        existingUser.setDescription("OLD DESCRIPTION");
        existingUser.setInterests(List.of());

        // Arrange - incoming request
        DsaUser request = new DsaUser();

        request.setUserId(1000);
        request.setUsername("UPDATED USERNAME");
        request.setPassword("UPDATED PASSWORD");
        request.setEmail("UPDATED EMAIL");
        request.setFirstname("UPDATED FIRSTNAME");
        request.setLastname("UPDATED LASTNAME");
        request.setDescription("UPDATED DESCRIPTION");
        request.setInterests(List.of());

        // Mock SELECT
        when(jdbcTemplate.queryForObject(
                anyString(),
                eq(dsaUserMapper),
                eq(1000L)
        )).thenReturn(existingUser);

        // Mock UPDATE
        when(jdbcTemplate.update(
                anyString(),
                any(Object[].class)
        )).thenReturn(1);

        // Act
        int result =
                dsaUserRepository.updateDsaUser(request);

        // Assert
        assertEquals(1, result);

        assertEquals(
                "UPDATED USERNAME",
                existingUser.getUsername()
        );

        assertEquals(
                "UPDATED PASSWORD",
                existingUser.getPassword()
        );

        assertEquals(
                "UPDATED EMAIL",
                existingUser.getEmail()
        );

        assertEquals(
                "UPDATED FIRSTNAME",
                existingUser.getFirstname()
        );

        assertEquals(
                "UPDATED LASTNAME",
                existingUser.getLastname()
        );

        assertEquals(
                "UPDATED DESCRIPTION",
                existingUser.getDescription()
        );

        assertEquals(
                List.of(),
                existingUser.getInterests()
        );

        assertEquals(
                1000L,
                existingUser.getUserId()
        );

        // Verify SELECT
        verify(jdbcTemplate, times(1))
                .queryForObject(
                        anyString(),
                        eq(dsaUserMapper),
                        eq(1000L)
                );

        // Verify UPDATE
        verify(jdbcTemplate, times(1))
                .update(
                        anyString(),
                        any(Object[].class)
                );
    }


    @Test
    public void deleteDsaUserTest() {

        // Arrange
        long userId = 1000L;

        when(jdbcTemplate.update(
                anyString(),
                eq(userId)
        )).thenReturn(1);

        // Act
        int result =
                dsaUserRepository.deleteDsaUser(userId);

        // Assert
        assertEquals(1, result);

        verify(jdbcTemplate, times(1))
                .update(
                        anyString(),
                        eq(userId)
                );
    }
}