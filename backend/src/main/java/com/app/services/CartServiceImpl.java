package com.app.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.BookingDTO;
import com.app.dto.PaymentDTO;
import com.app.entities.Booking;
import com.app.entities.Payment;
import com.app.entities.Services;
import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.BookingRepository;
import com.app.repository.PaymentRepository;
import com.app.repository.ServicesRepository;
import com.app.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) throws ResourceNotFoundException {
        User customer = userRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + bookingDTO.getCustomerId()));

        User serviceProvider = userRepository.findById(bookingDTO.getServiceProviderId())
                .orElseThrow(() -> new ResourceNotFoundException("Service provider not found with ID: " + bookingDTO.getServiceProviderId()));

        Payment payment = paymentRepository.findById(bookingDTO.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID: " + bookingDTO.getPaymentId()));

        List<Services> services = servicesRepository.findAllById(bookingDTO.getServiceIds());

        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        booking.setCustomer(customer);
        booking.setServiceProvider(serviceProvider);
        booking.setPayment(payment);
        booking.setServices(services);

        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDTO.class);
    }

    @Override
    public PaymentDTO processPayment(PaymentDTO paymentDTO) throws ResourceNotFoundException {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        Payment savedPayment = paymentRepository.save(payment);
        return modelMapper.map(savedPayment, PaymentDTO.class);
    }
}
