package com.app.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SigninRequest {
	
//	@NotEmpty(message = "Email can't be blank")
//	@Email(message = "Invalid email format")
	
	private String emailId;
	
//	@NotEmpty
//	@Length(min = 3,max=20,message = "Invalid password length")
	private String password;
}