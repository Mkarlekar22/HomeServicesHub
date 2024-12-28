package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO extends BaseDTO {

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    private double amount;

    @NotNull(message = "Payment date cannot be null")
    private LocalDate paymentDate;

    @NotBlank(message = "Payment method cannot be blank")
    @Pattern(regexp = "^(Credit Card|Debit Card|PayPal|Bank Transfer)$", message = "Payment method must be one of: Credit Card, Debit Card, PayPal, Bank Transfer")
    private String paymentMethod;
}
