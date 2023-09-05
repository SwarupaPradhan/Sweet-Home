package com.sweethome.bookingservice.services;

import com.sweethome.bookingservice.dto.PaymentDTO;
import com.sweethome.bookingservice.entities.BookingInfoEntity;

public interface BookingService {

    public BookingInfoEntity createBooking(BookingInfoEntity bookingInfo);

    public BookingInfoEntity getBookingDetails(int id);

    public BookingInfoEntity processPayment(int bookingId, PaymentDTO paymentDTO);

}