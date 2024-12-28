package com.app.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRatingsDTO extends BaseDTO {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Min(value = 5, message = "Rating cannot exceed 5")
    private int rating;

    @Size(max = 255, message = "Comment must be less than 255 characters")
    private String comment;

    @NotNull(message = "Customer ID cannot be null")
    @Min(value = 1, message = "Customer ID must be a positive number")
    private Long customer;

    @NotNull(message = "Service Provider ID cannot be null")
    @Min(value = 1, message = "Service Provider ID must be a positive number")
    private Long serviceProvider;
}
