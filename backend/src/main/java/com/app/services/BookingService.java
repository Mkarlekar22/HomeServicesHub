package com.app.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Booking;
import com.app.entities.BookingStatus;
import com.app.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Method to add a new booking
    public Booking addBooking(Booking booking) {
        booking.setStatus(BookingStatus.PENDING); // Set initial status
        return bookingRepository.save(booking);
    }

    // Method to cancel a booking
    public Booking cancelBooking(Long bookingId) throws Exception {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (!bookingOptional.isPresent()) {
            throw new Exception("Booking not found with ID: " + bookingId);
        }
        Booking booking = bookingOptional.get();
        booking.setStatus(BookingStatus.CANCELED); // Update the status to CANCELED
        return bookingRepository.save(booking); // Save the updated booking
    }

    // Method to get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Method to get a booking by ID
    public Booking getBookingById(Long id) throws Exception {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new Exception("Booking not found with ID: " + id));
    }
}
