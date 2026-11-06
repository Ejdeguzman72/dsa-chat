package com.dsa.chat.repository;

import java.time.OffsetDateTime;
import java.util.List;

public interface PasswordResetRepository {
    List<Long> findUsersByEmail(String email);
    int createPasswordToken(long userId, String tokenHash, OffsetDateTime expiresAt);
    List<Long> findUsersToResetPassword(String tokenHash);
    int updateUserPassword(String encodedPassword,long userId);
    int deletePasswordResetToken(long userId);
}
