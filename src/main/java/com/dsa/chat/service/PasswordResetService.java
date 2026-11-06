package com.dsa.chat.service;

import com.dsa.chat.config.ResetTokenUtil;
import com.dsa.chat.repository.PasswordResetRepositoryImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class PasswordResetService {
    private final PasswordResetRepositoryImpl passwordResetRepository;
    private final PasswordResetEmailService passwordResetEmailService;
    private final PasswordEncoder passwordEncoder;
    public PasswordResetService(PasswordResetRepositoryImpl passwordResetRepository, PasswordResetEmailService passwordResetEmailService, PasswordEncoder passwordEncoder) {
        this.passwordResetRepository = passwordResetRepository;
        this.passwordResetEmailService = passwordResetEmailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void forgotPassword(String email) {
        List<Long> userIds = passwordResetRepository.findUsersByEmail(email);

        // Do not reveal whether the account exists.
        if (userIds.isEmpty()) {
            return;
        }

        Long userId = userIds.get(0);

        String token = ResetTokenUtil.generateToken();
        String tokenHash = ResetTokenUtil.hashToken(token);

        passwordResetRepository.deletePasswordResetToken(userId);
        passwordResetRepository.createPasswordToken(userId,tokenHash,OffsetDateTime.now(ZoneOffset.systemDefault()).plusMinutes(30));

        passwordResetEmailService.sendResetLink(email, token);
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {

        if (token == null || token.isBlank()
                || newPassword == null
                || newPassword.length() < 12) {
            throw new ResponseStatusException(
                BAD_REQUEST, "Invalid token or password"
            );
        }

        String tokenHash = ResetTokenUtil.hashToken(token);

        List<Long> userIds = passwordResetRepository.findUsersToResetPassword(tokenHash);

        if (userIds.isEmpty()) {
            throw new ResponseStatusException(
                BAD_REQUEST, "Invalid or expired reset link"
            );
        }

        Long userId = userIds.get(0);

        String encodedPassword = passwordEncoder.encode(newPassword);

        passwordResetRepository.updateUserPassword(encodedPassword,userId);

        passwordResetRepository.deletePasswordResetToken(userId);
    }
}