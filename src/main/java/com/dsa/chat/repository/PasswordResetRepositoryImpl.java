package com.dsa.chat.repository;

import com.dsa.chat.domain.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

@Repository
public class PasswordResetRepositoryImpl implements PasswordResetRepository {
    private JdbcTemplate jdbcTemplate;
    public static final String RETRIEVE_USERS_BY_EMAIL = "SELECT user_id FROM dsa_user WHERE LOWER(email) = LOWER(?)";
    public static final String RETRIEVE_USER_IDS_FOR_PASSWORD_RESET = " SELECT user_id " +
            "FROM password_reset_tokens " +
            "WHERE token_hash = ? " +
            "AND used_at IS NULL " +
            "AND expires_at > NOW() FOR UPDATE";
    public static final String DELETE_PASSWORD_RESET_TOKEN = "DELETE FROM password_reset_tokens WHERE user_id = ?";
    public static final String CREATE_PASSWORD_RESET_TOKEN = "INSERT INTO PASSWORD_RESET_TOKENS (user_id,token_hash,expires_at) VALUES(?,?,?)";
    public static final String UPDATE_DSA_USER_PASSWORD = "UPDATE dsa_user SET password = ? WHERE user_id = ?";
    @Autowired
    public PasswordResetRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Long> findUsersByEmail(String email) {
        List<Long> userIdList = jdbcTemplate.queryForList(RETRIEVE_USERS_BY_EMAIL,Long.class,email);
        return userIdList;
    }

    @Override
    public int createPasswordToken(long userId, String tokenHash, OffsetDateTime expiresAt) {
        return jdbcTemplate.update(CREATE_PASSWORD_RESET_TOKEN,userId,tokenHash,expiresAt);
    }

    @Override
    public List<Long> findUsersToResetPassword(String tokenHash) {
        List<Long> userIdList = jdbcTemplate.queryForList(RETRIEVE_USER_IDS_FOR_PASSWORD_RESET,Long.class,tokenHash);
        return userIdList;
    }

    @Override
    public int updateUserPassword(String encodedPassword, long userId) {
        return jdbcTemplate.update(UPDATE_DSA_USER_PASSWORD,encodedPassword,userId);
    }

    @Override
    public int deletePasswordResetToken(long userId) {
        return jdbcTemplate.update(DELETE_PASSWORD_RESET_TOKEN,userId);
    }
}
