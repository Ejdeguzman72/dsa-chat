package com.dsa.chat.repository;

import com.dsa.chat.domain.DSAUserMapper;
import com.dsa.chat.entity.DsaUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.tree.RowMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
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

        when(jdbcTemplate.queryForObject(anyString(),eq(dsaUserMapper))).thenReturn(expectedDsaUser);
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
                eq("TEST DESCRIPTION"),
                eq("TEST FIRSTNAME"),
                eq("TEST LASTNAME"),
                eq(LocalDate.now()),
                eq(LocalDate.now())
        )).thenReturn(1);

        int result = dsaUserRepository.registerNewDsaUser(dsaUser);
        assertEquals(expectedInsertedCount,result);
    }

    @Test
    public void updateDsaUserTest() {

        // Arrange - existing user returned from database
        DsaUser existingUser = new DsaUser();
        existingUser.setUserId(1000);

        // Arrange - updated information coming from request
        DsaUser request = new DsaUser();
        request.setUserId(1000);
        request.setUsername("UPDATED USERNAME");
        request.setPassword("UPDATED PASSWORD");
        request.setEmail("UPDATED EMAIL");
        request.setFirstname("UPDATED FIRSTNAME");
        request.setLastname("UPDATED LASTNAME");
        request.setDescription("UPDATED DESCRIPTION");
        request.setInterests(List.of());

        // Mock retrieving the existing user
        when(jdbcTemplate.queryForObject(
                anyString(),
                eq(dsaUserMapper)
        )).thenReturn(existingUser);

        // Mock successful database update
        when(jdbcTemplate.update(
                anyString(),
        eq("UPDATED USERNAME"),
        eq("UPDATED PASSWORD"),
        eq("UPDATED EMAIL"), eq("UPDATED FIRSTNAME"), eq("UPDATED LASTNAME"),
        eq("UPDATED DESCRIPTION"),
                eq(List.of()), eq(1000)
        )).thenReturn(1);

        // Act
        int result = dsaUserRepository.updateDsaUser(request);

        // Assert
        assertEquals(1, result);

        assertEquals("UPDATED USERNAME", existingUser.getUsername());
        assertEquals("UPDATED PASSWORD", existingUser.getPassword());
        assertEquals("UPDATED EMAIL", existingUser.getEmail());
        assertEquals("UPDATED FIRSTNAME", existingUser.getFirstname());
        assertEquals("UPDATED LASTNAME", existingUser.getLastname());
        assertEquals("UPDATED DESCRIPTION", existingUser.getDescription());
        assertEquals(List.of(), existingUser.getInterests());
        assertEquals(1000L, existingUser.getUserId());

        // Verify queryForObject was called
        verify(jdbcTemplate, times(1)).queryForObject(
                anyString(),
                eq(dsaUserMapper)
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
