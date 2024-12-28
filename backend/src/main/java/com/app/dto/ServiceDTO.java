package com.app.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.app.entities.ServiceType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDTO extends BaseDTO {

    @NotBlank(message = "Service name cannot be blank")
    @Size(max = 100, message = "Service name must be less than 100 characters")
    private String serviceName;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Service type cannot be null")
    private ServiceType serviceType;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private double price;

    @NotNull(message = "Service provider ID cannot be null")
    private Long serviceProviderId;
}
