package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.app.entities.BookingStatus;
import com.app.entities.TimeSlot;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingDTO extends BaseDTO {

    @NotNull(message = "Date and time cannot be null")
    private LocalDate dateTime;

    @NotNull(message = "Status cannot be null")
    private BookingStatus status;

    @NotNull(message = "Time slot cannot be null")
    private TimeSlot timeSlot;

    @NotNull(message = "Customer ID cannot be null")
    @Min(value = 1, message = "Customer ID must be a positive number")
    private Long customerId;

    @NotNull(message = "Service provider ID cannot be null")
    @Min(value = 1, message = "Service provider ID must be a positive number")
    private Long serviceProviderId;

    @NotNull(message = "Payment ID cannot be null")
    @Min(value = 1, message = "Payment ID must be a positive number")
    private Long paymentId;

    @NotNull(message = "Service IDs cannot be null")
    private List<@Min(value = 1, message = "Service ID must be a positive number") Long> serviceIds;
}
