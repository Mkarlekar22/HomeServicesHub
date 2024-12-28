package com.app.dto;

public class ApiResponseDTO {
    private String message;

    public ApiResponseDTO(String message) {
        this.message = message;
    }

    // Getter and setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
