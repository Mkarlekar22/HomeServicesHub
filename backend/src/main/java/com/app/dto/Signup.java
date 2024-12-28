package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.app.entities.Address;
import com.app.entities.Gender;
import com.app.entities.ServiceType;
import com.app.entities.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Signup extends BaseDTO{
	private String fname;

	private String lname;

	@Email
	private String emailId;

	@Pattern(regexp = "^[1234567890]\\d{9}$")
	private String contactNumber;

	@NotBlank
	@Pattern(
		    regexp = "^[A-Z](?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{4,19}$",
		    message = "Password must start with an uppercase letter, be between 5 and 20 characters long, and contain at least one digit, one lowercase letter, and one special character from [#@$*]"
	)
	private String password;

	private Gender gender;

	private UserRole role;

	private Address address;
	
	private ServiceType serviceType;
	
	
	
}
