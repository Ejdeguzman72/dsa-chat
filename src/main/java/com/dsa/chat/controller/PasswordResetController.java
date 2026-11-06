package com.dsa.chat.controller;

import com.dsa.chat.domain.ForgotPasswordRequest;
import com.dsa.chat.domain.ResetPasswordRequest;
import com.dsa.chat.domain.UriConstants;
import com.dsa.chat.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PasswordResetController {

    private final PasswordResetService service;

    public PasswordResetController(PasswordResetService service) {
        this.service = service;
    }

    @PostMapping(UriConstants.FORGOT_PASSWORD)
    public ResponseEntity<?> forgotPassword(
            @RequestBody ForgotPasswordRequest request
    ) {
        service.forgotPassword(request.getEmail());

        return ResponseEntity.ok(Map.of(
            "message",
            "If an account exists, a reset link will be sent."
        ));
    }

    @PostMapping(UriConstants.RESET_PASSWORD)
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest request
    ) {
        service.resetPassword(
            request.getToken(),
            request.getNewPassword()
        );

        return ResponseEntity.ok(Map.of(
            "message", "Password updated successfully."
        ));
    }
}