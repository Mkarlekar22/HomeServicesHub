package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingDTO;
import com.app.dto.PaymentDTO;
import com.app.exception.ResourceNotFoundException;
import com.app.services.CartService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/payment")
    public ResponseEntity<PaymentDTO> processPayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            PaymentDTO savedPayment = cartService.processPayment(paymentDTO);
            return ResponseEntity.ok(savedPayment);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            BookingDTO savedBooking = cartService.createBooking(bookingDTO);
            return ResponseEntity.ok(savedBooking);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
