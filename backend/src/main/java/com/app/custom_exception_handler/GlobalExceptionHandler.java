package com.app.custom_exception_handler;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.app.dto.ApiResponseDTO;
import com.app.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("Method argument not valid", e);
        List<FieldError> fieldErrors = e.getFieldErrors();
        Map<String, String> map = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponseDTO handleResourceNotFoundException(ResourceNotFoundException e) {
        logger.error("Resource not found", e);
        return new ApiResponseDTO("Resource not found: " + e.getMessage());
    }

//    // Handle AuthenticationException
//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ApiResponseDTO handleAuthenticationException(AuthenticationException e) {
//        logger.error("Authentication exception", e);
//        return new ApiResponseDTO("Authentication failed: " + e.getMessage());
//    }

    // Handle AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponseDTO handleAccessDeniedException(AccessDeniedException e) {
        logger.error("Access denied exception", e);
        return new ApiResponseDTO("Access denied: " + e.getMessage());
    }

    // Handle HttpClientErrorException
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDTO handleHttpClientErrorException(HttpClientErrorException e) {
        logger.error("HTTP client error", e);
        return new ApiResponseDTO("HTTP client error: " + e.getMessage());
    }

    // Catch-all for other exceptions
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseDTO handleAnyException(RuntimeException e) {
        logger.error("Unhandled exception", e);
        return new ApiResponseDTO("An unexpected error occurred: " + e.getMessage());
    }
}
