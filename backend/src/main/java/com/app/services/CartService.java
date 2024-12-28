package com.app.services;

import com.app.dto.BookingDTO;
import com.app.dto.PaymentDTO;
import com.app.exception.ResourceNotFoundException;

public interface CartService {
    BookingDTO createBooking(BookingDTO bookingDTO) throws ResourceNotFoundException;
    PaymentDTO processPayment(PaymentDTO paymentDTO) throws ResourceNotFoundException;
}
