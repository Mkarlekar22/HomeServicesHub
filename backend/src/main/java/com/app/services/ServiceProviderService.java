package com.app.services;

import java.util.List;

import com.app.dto.BookingDTO;
import com.app.dto.CustomerRatingsDTO;
import com.app.dto.PaymentDTO;
import com.app.dto.UserDTO;
import com.app.exception.ResourceNotFoundException;

public interface ServiceProviderService {


    UserDTO getServiceProviderById(Long userId) throws ResourceNotFoundException;

    List<CustomerRatingsDTO> getAllRatings(Long userId) throws ResourceNotFoundException;

    
    List<BookingDTO> getAllBookings(Long userId) throws ResourceNotFoundException;

    
    BookingDTO getBookingStatus(Long bookingId) throws ResourceNotFoundException;

    
    List<PaymentDTO> getPaymentHistory(Long userId) throws ResourceNotFoundException;

   
    UserDTO updateServiceProvider(Long userId, UserDTO userDTO) throws ResourceNotFoundException;
}
