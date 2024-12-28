package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.app.entities.Gender;
import com.app.entities.ServiceType;
import com.app.entities.UserRole;

import lombok.Data;

@Data
public class UserDTO extends BaseDTO{

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 255, message = "First name should not exceed 255 characters")
    private String fname;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 255, message = "Last name should not exceed 255 characters")
    private String lname;

    @NotBlank(message = "Email cannot be blank")
    @Size(max = 255, message = "Email should not exceed 255 characters")
    private String emailId;

    @NotBlank(message = "Contact number cannot be blank")
    @Size(max = 20, message = "Contact number should not exceed 20 characters")
    private String contactNumber;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @NotNull(message = "Role cannot be null")
    private UserRole role;

    private AddressDTO address;

    private ServiceType serviceType; // Optional, can be null for non-service provider roles
}
