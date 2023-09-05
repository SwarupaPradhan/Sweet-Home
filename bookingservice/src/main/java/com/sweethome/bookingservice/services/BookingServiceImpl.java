package com.sweethome.bookingservice.services;

import com.sweethome.bookingservice.dao.BookingDao;
import com.sweethome.bookingservice.dto.PaymentDTO;
import com.sweethome.bookingservice.entities.BookingInfoEntity;
import com.sweethome.bookingservice.exceptiion.BookingServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Objects;


@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingDao bookingDao;


    @Override
    public BookingInfoEntity createBooking(BookingInfoEntity bookingInfo) {
           bookingInfo.setRoomPrice(Math.toIntExact((1000 * bookingInfo.getNumOfRooms() * (ChronoUnit.DAYS.between((Temporal) bookingInfo.getFromDate(), (Temporal) bookingInfo.getToDate())))));
           bookingInfo.setRoomNumbers(bookingInfo.getRandomNumbers(bookingInfo.getNumOfRooms()));
           bookingInfo.setBookedOn(bookingInfo.getFromDate());
           return bookingDao.save(bookingInfo);

    }


    public BookingInfoEntity processPayment(int bookingId, PaymentDTO paymentDTO){
        BookingInfoEntity bookingInfo = bookingDao.findById(bookingId)
                .orElseThrow(() -> new BookingServiceException("Invalid Booking Id", 400));

        if (!isValidPaymentMode(paymentDTO.getPaymentMode())) {
            throw new BookingServiceException("Invalid mode of payment", 400);
        }

        if (paymentDTO.getPaymentMode().equals("UPI")) {
            if (paymentDTO.getCardNumber().trim().length() != 0) {
                throw new BookingServiceException("Invalid payment details: Card number provided for UPI payment", 400);
            }
        } else if (paymentDTO.getPaymentMode().equals("CARD")) {
            if (paymentDTO.getUpiId().trim().length() != 0) {
                throw new BookingServiceException("Invalid payment details: UPI ID provided for Card payment", 400);
            }
        }

        // process transaction with payment service
        int transactionId = processTransaction(paymentDTO);
        bookingInfo.setTransactionId(transactionId);

        // save booking with new transactionId and print booking details
        BookingInfoEntity savedBooking = bookingDao.save(bookingInfo);

        String message = "Booking confirmed for user with Aadhaar number: "
                + savedBooking.getAadharNumber()
                + "    |    "
                + "Here are the booking details:    " + savedBooking;

        System.out.println(message);
        return savedBooking;
    }

    private Integer processTransaction(PaymentDTO paymentRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        String paymentServiceUrl = "http://localhost:8083/transaction";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentDTO> request = new HttpEntity<>(paymentRequestDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(paymentServiceUrl, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return Integer.parseInt(Objects.requireNonNull(response.getBody()));
        } else {
            throw new BookingServiceException("Transaction was unsuccessful", 500);
        }
    }

    private boolean isValidPaymentMode(String paymentMode) {
        return paymentMode.equals("UPI") || paymentMode.equals("CARD");
    }


    public BookingInfoEntity getBookingDetails(int id){
        return bookingDao.findById(id).get();


    }

}