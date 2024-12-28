package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"address"})
@Entity(name = "users")
public class User extends BaseEntity{
	
	@Column(name = "fname", length = 255, unique = false)
	private String fname;
	
	@Column(name = "lname", length = 255, unique = false)
	private String lname;

	@Column(name = "email_id", length = 255, unique = true)
	@NotBlank(message = "Email cannot be blank")
	private String emailId;
	
	@Column(name = "contact_number", length = 20, unique = true)
	@NotBlank(message = "contact number cannot be blank")
	private String contactNumber;
	
	@Column(name = "password", length = 255)
	@NotBlank(message = "password cannot be blank")
	private String password;
	
	@Column(name = "gender", length = 55)
	@Enumerated(EnumType.STRING)
	//@NotBlank(message = "gender cannot be blank")
	private Gender gender;

	@Column(name = "user_role", length = 255)
    @Enumerated(EnumType.STRING)
	//@NotBlank(message = "Role cannot be blank")
    private UserRole role;
	
	@Embedded	
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private ServiceType serviceType;

	public User(String fname, String lname, String emailId,
			String contactNumber, String password, Gender gender, UserRole role, ServiceType serviceType) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.serviceType = serviceType;
	}

	public UserRole getRole() {
		return role;
	}

	
	
	
	
	
	

}
