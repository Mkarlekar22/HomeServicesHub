package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO extends BaseDTO {

    @NotBlank(message = "House number cannot be blank")
    @Size(max = 50, message = "House number must be less than 50 characters")
    private String houseNo;
    
    @NotBlank(message = "Street cannot be blank")
    @Size(max = 100, message = "Street must be less than 100 characters")
    private String street;
    
    @NotBlank(message = "City cannot be blank")
    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;
    
    @NotBlank(message = "State cannot be blank")
    @Size(max = 50, message = "State must be less than 50 characters")
    private String state;
    
    @NotNull(message = "Postal code cannot be null")
    @Pattern(regexp = "\\d{5,6}", message = "Postal code must be a 5 or 6 digit number")
    private String postalCode;
    
    @NotBlank(message = "Country cannot be blank")
    @Size(max = 50, message = "Country must be less than 50 characters")
    private String country;
}
