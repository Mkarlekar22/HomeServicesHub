package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Booking;
import com.app.entities.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find bookings by customer
    List<Booking> findByCustomer(User customer);

    // Find bookings by service provider
    List<Booking> findByServiceProvider(User serviceProvider);

}
