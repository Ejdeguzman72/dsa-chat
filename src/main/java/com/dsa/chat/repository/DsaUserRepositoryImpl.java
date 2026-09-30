package com.dsa.chat.repository;

import com.dsa.chat.domain.DSAUserMapper;
import com.dsa.chat.entity.DsaUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DsaUserRepositoryImpl implements DsaUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    DSAUserMapper dsaUserMapper = new DSAUserMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(DsaUserRepositoryImpl.class);
    public static final String RETRIEVE_ALL_USERS = "SELECT USER_ID,USERNAME,PASSWORD,EMAIL,FIRSTNAME,LASTNAME,DESCRIPTION,INTERESTS,CREATION_DATE,LAST_UPDATETIME FROM DSA_USER";
    public static final String RETRIEVE_USER_BY_ID = "SELECT USER_ID,USERNAME,PASSWORD,EMAIL,FIRSTNAME,LASTNAME,DESCRIPTION,INTERESTS,CREATION_DATE,LAST_UPDATETIME FROM DSA_USER WHERE USER_ID = ?";
    public static final String RETRIEVE_USER_BY_USERNAME = "SELECT USER_ID,USERNAME,PASSWORD,EMAIL,FIRSTNAME,LASTNAME,DESCRIPTION,INTERESTS,CREATION_DATE,LAST_UPDATETIME FROM DSA_USER WHERE USERNAME = ?";
    public static final String REGISTER_NEW_DSA_USER = "INSERT INTO DSA_USER (USERNAME,PASSWORD,EMAIL,FIRSTNAME,LASTNAME,DESCRIPTION,INTERESTS,CREATION_DATE,LAST_UPDATETIME) VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_DSA_USER_INFO = "UPDATE DSA_USER SET USERNAME = ?,PASSWORD = ?,EMAIL = ?,FIRSTNAME = ?,LASTNAME = ?,DESCRIPTION = ?,INTERESTS = ?,CREATION_DATE = ?,LAST_UPDATETIME = ? WHERE USER_ID = ?";
    public static final String DELETE_DSA_USER = "DELETE FROM DSA_USER WHERE USER_ID = ?";

    @Override
    public List<DsaUser> retrieveAllUsers() {
        List<DsaUser> list = jdbcTemplate.query(RETRIEVE_ALL_USERS,dsaUserMapper);
        LOGGER.info("Retrieving all user information...");

        return list;
    }

    @Override
    public DsaUser retrieveUserById(long userId) {
        DsaUser dsaUser = jdbcTemplate.queryForObject(RETRIEVE_USER_BY_ID,dsaUserMapper);
        LOGGER.info("Retrieving user with ID: " + userId);

        return dsaUser;
    }

    @Override
    public DsaUser retrieveUserByUsername(String username) {
        DsaUser dsaUser = jdbcTemplate.queryForObject(RETRIEVE_USER_BY_USERNAME, dsaUserMapper,username);
        LOGGER.info("Retrieving user with username: " + username);

        return dsaUser;
    }

    @Override
    public int registerNewDsaUser(DsaUser request) {
        int result = 0;
        if (request != null) {
           result = jdbcTemplate.update(REGISTER_NEW_DSA_USER,new Object[] {
                   request.getUsername(),
                   request.getPassword(),
                   request.getEmail(),
                   request.getFirstname(),
                   request.getLastname(),
                   request.getDescription(),
                   request.getInterests(),
                   LocalDate.now(),
                   LocalDate.now()
            });
        }

        return result;
    }

    @Override
    public int updateDsaUser(DsaUser request) {
        int result = 0;
        DsaUser dsaUser = jdbcTemplate.queryForObject(RETRIEVE_USER_BY_ID,dsaUserMapper);
        if (dsaUser != null) {
            dsaUser.setUsername(request.getUsername());
            dsaUser.setPassword(request.getPassword());
            dsaUser.setEmail(request.getEmail());
            dsaUser.setFirstname(request.getFirstname());
            dsaUser.setLastname(request.getLastname());
            dsaUser.setDescription(request.getDescription());
            dsaUser.setInterests(request.getInterests());
            dsaUser.setCreationDate(LocalDate.now());
            dsaUser.setLastUpdatetime(LocalDate.now());

            result = jdbcTemplate.update(UPDATE_DSA_USER_INFO,new Object[] {
                    dsaUser.getUsername(),
                    dsaUser.getPassword(),
                    dsaUser.getEmail(),
                    dsaUser.getFirstname(),
                    dsaUser.getLastname(),
                    dsaUser.getDescription(),
                    dsaUser.getInterests(),
                    dsaUser.getCreationDate(),
                    dsaUser.getLastUpdatetime(),
                    dsaUser.getUserId()
            });

            LOGGER.info("Updating dsa user with ID: " + request.getUserId());
        }

        return result;
    }

    @Override
    public int deleteDsaUser(long userId) {
        int result = 0;
        if (userId > 0) {
            result = jdbcTemplate.update(DELETE_DSA_USER,Long.class);
        }

        return result;
    }
}

