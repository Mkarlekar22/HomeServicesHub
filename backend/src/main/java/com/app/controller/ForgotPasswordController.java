package com.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.ApiResponse;
import com.app.exception.ResourceNotFoundException;
import com.app.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestBody Map<String, String> data) {
        String emailId = data.get("emailId");
        String newPassword = data.get("newPassword");

        try {
            userService.forgetPassword(emailId, newPassword);
            return ResponseEntity.ok(new ApiResponse("Password reset successfully", true));
        } catch (ResourceNotFoundException e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), false));
        }
    }
}

