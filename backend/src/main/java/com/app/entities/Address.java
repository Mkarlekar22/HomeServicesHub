package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Address{
	
	@Column(name = "house_no", length = 255, unique = false)
	private String houseNo;
	
	@Column(name = "street", length = 255, unique = false)
    private String street;
	
	@Column(name = "city", length = 255, unique = false)
    private String city;
	
	@Column(name = "state", length = 255, unique = false)
    private String state;
	
	@Column(name = "postal_code", unique = false)
    private int postalCode;
	
	@Column(name = "country", length = 255, unique = false)
    private String country;

}
