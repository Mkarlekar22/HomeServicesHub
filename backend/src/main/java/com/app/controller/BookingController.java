package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Booking;
import com.app.services.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // API endpoint to add a new booking
    @PostMapping("/addNewBooking")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.addBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }

    // API endpoint to cancel a booking
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        try {
            Booking canceledBooking = bookingService.cancelBooking(id);
            return ResponseEntity.ok(canceledBooking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // API endpoint to get all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // API endpoint to get a booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        try {
            Booking booking = bookingService.getBookingById(id);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

