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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
        List<DsaUser> expectedUsers = new ArrayList<>();
        DsaUser testUser1 = new DsaUser();
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

        List<DsaUser> actualUsers =
                dsaUserRepository.retrieveAllUsers();

        assertEquals(expectedUsers, actualUsers);
        assertEquals(expectedUsers.get(0).getUsername(),actualUsers.get(0).getUsername());
        assertEquals(expectedUsers.get(0).getFirstname(), actualUsers.get(0).getFirstname());
    }

    @Test
    public void retrieveUserByIdTest() {
        DsaUser expectedDsaUser = new DsaUser();
        expectedDsaUser.setUserId(1000);
        expectedDsaUser.setUsername("TEST USERNAME");
        expectedDsaUser.setPassword("TEST PASSWORD");
        expectedDsaUser.setEmail("TEST EMAIL");
        expectedDsaUser.setDescription("TEST DESCRIPTION");
        expectedDsaUser.setInterests(List.of());
        expectedDsaUser.setFirstname("TEST FIRSTNAME");
        expectedDsaUser.setLastname("TEST LASTNAME");

        when(jdbcTemplate.queryForObject(anyString(),eq(dsaUserMapper))).thenReturn(expectedDsaUser);
        DsaUser actualUser = dsaUserRepository.retrieveUserById(1000);

        assertEquals(expectedDsaUser,actualUser);
    }

    @Test
    public void retrieveUserByUsernameTest() {
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

        when(jdbcTemplate.queryForObject(anyString(),eq(dsaUserMapper),eq("TEST USERNAME"))).thenReturn(expectedDsaUser);
        DsaUser actualUser = dsaUserRepository.retrieveUserByUsername("TEST USERNAME");

        assertEquals(expectedDsaUser,actualUser);
    }

    @Test
    public void registerNewDsaUserTest() {
        DsaUser dsaUser = new DsaUser();
        dsaUser.setUsername("TEST USERNAME");
        dsaUser.setPassword("TEST PASSWORD");
        dsaUser.setEmail("TEST EMAIL");
        dsaUser.setDescription("TEST DESCRIPTION");
        dsaUser.setInterests(List.of());
        dsaUser.setFirstname("TEST FIRSTNAME");
        dsaUser.setLastname("TEST LASTNAME");

        int expectedInsertedCount = 1;

        when(jdbcTemplate.update(
                anyString(),
                eq("TEST USERNAME"),
                eq("TEST PASSWORD"),
                eq("TEST EMAIL"),
                eq("TEST FIRSTNAME"),
                eq("TEST LASTNAME"),
                eq("TEST DESCRIPTION"),
                eq(List.of()),
                any(LocalDate.class),
                any(LocalDate.class)
        )).thenReturn(1);

        int result = dsaUserRepository.registerNewDsaUser(dsaUser);
        assertEquals(expectedInsertedCount,result);
    }

    @Test
    public void updateDsaUserTest() {

        // -----------------------------------
        // Arrange - user currently in database
        // -----------------------------------

        DsaUser existingUser = new DsaUser();

        existingUser.setUserId(1000);
        existingUser.setUsername("OLD USERNAME");
        existingUser.setPassword("OLD PASSWORD");
        existingUser.setEmail("OLD EMAIL");
        existingUser.setFirstname("OLD FIRSTNAME");
        existingUser.setLastname("OLD LASTNAME");
        existingUser.setDescription("OLD DESCRIPTION");
        existingUser.setInterests(List.of());


        // -----------------------------------
        // Arrange - incoming update request
        // -----------------------------------

        DsaUser request = new DsaUser();

        request.setUserId(1000);
        request.setUsername("UPDATED USERNAME");
        request.setPassword("UPDATED PASSWORD");
        request.setEmail("UPDATED EMAIL");
        request.setFirstname("UPDATED FIRSTNAME");
        request.setLastname("UPDATED LASTNAME");
        request.setDescription("UPDATED DESCRIPTION");
        request.setInterests(List.of());


        // -----------------------------------
        // Mock SELECT
        // -----------------------------------

        when(jdbcTemplate.queryForObject(
                anyString(),
                eq(dsaUserMapper),
                eq(1000)
        )).thenReturn(existingUser);


        // -----------------------------------
        // Mock UPDATE
        // -----------------------------------

        when(jdbcTemplate.update(
                anyString(),
                eq("UPDATED USERNAME"),
                eq("UPDATED PASSWORD"),
                eq("UPDATED EMAIL"),
                eq("UPDATED FIRSTNAME"),
                eq("UPDATED LASTNAME"),
                eq("UPDATED DESCRIPTION"),
                eq(List.of()),
                any(LocalDate.class),
                any(LocalDate.class),
                eq(1000)
        )).thenReturn(1);


        // -----------------------------------
        // Act
        // -----------------------------------

        int result =
                dsaUserRepository.updateDsaUser(request);


        // -----------------------------------
        // Assert
        // -----------------------------------

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
                1000,
                existingUser.getUserId()
        );


        // -----------------------------------
        // Verify SELECT
        // -----------------------------------

        verify(jdbcTemplate, times(1))
                .queryForObject(
                        anyString(),
                        eq(dsaUserMapper),
                        eq(1000)
                );


        // -----------------------------------
        // Verify UPDATE
        // -----------------------------------

        verify(jdbcTemplate, times(1))
                .update(
                        anyString(),
                        eq("UPDATED USERNAME"),
                        eq("UPDATED PASSWORD"),
                        eq("UPDATED EMAIL"),
                        eq("UPDATED FIRSTNAME"),
                        eq("UPDATED LASTNAME"),
                        eq("UPDATED DESCRIPTION"),
                        eq(List.of()),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        eq(1000)
                );
    }

    @Test
    public void deleteDsaUserTest() {
        DsaUser expectedDsaUser = new DsaUser();
        expectedDsaUser.setUserId(1000);
        expectedDsaUser.setUsername("TEST USERNAME");
        expectedDsaUser.setPassword("TEST PASSWORD");
        expectedDsaUser.setEmail("TEST EMAIL");
        expectedDsaUser.setDescription("TEST DESCRIPTION");
        expectedDsaUser.setInterests(List.of());
        expectedDsaUser.setFirstname("TEST FIRSTNAME");
        expectedDsaUser.setLastname("TEST LASTNAME");

        int expected = 1;

        when(jdbcTemplate.update(
                anyString(),eq(Long.class)
        )).thenReturn(1);

        int result = dsaUserRepository.deleteDsaUser(1000);
        assertEquals(expected,result);
    }
}
