package com.sweethome.bookingservice.controller;


import com.sweethome.bookingservice.dto.BookingDTO;
import com.sweethome.bookingservice.dto.ErrorDTO;
import com.sweethome.bookingservice.dto.PaymentDTO;
import com.sweethome.bookingservice.entities.BookingInfoEntity;
import com.sweethome.bookingservice.exceptiion.BookingServiceException;
import com.sweethome.bookingservice.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/hotel")
public class BookingController {

    @Autowired
    private BookingService bookingService ;

    @Autowired
    ModelMapper modelMapper;



    @PostMapping(value="/booking", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBooking(@RequestBody BookingDTO booking){

        BookingInfoEntity newBooking = modelMapper.map(booking,BookingInfoEntity.class);
        BookingInfoEntity savedBooking = bookingService.createBooking(newBooking);

        BookingDTO savedBookingDTO = modelMapper.map(savedBooking,BookingDTO.class);

        return new ResponseEntity(savedBookingDTO, HttpStatus.CREATED);
    }


    @PostMapping("/booking/{bookingId}/transaction")
    public ResponseEntity<?> processPayment(@PathVariable int bookingId, @RequestBody PaymentDTO paymentDTO) {
        try {
            return new ResponseEntity<>(bookingService.processPayment(bookingId, paymentDTO), HttpStatus.OK);
        } catch (BookingServiceException e) {
            ErrorDTO errorResponse = new ErrorDTO(e.getMessage(), e.getStatusCode());
            return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getStatusCode()));
        }
    }



    @GetMapping(value = "/booking/{id}")
    public ResponseEntity getMovieBasedOnId(@PathVariable(name = "id") int id){
        BookingInfoEntity responseBooking = bookingService.getBookingDetails(id);

        BookingDTO responseMovieDTO = modelMapper.map(responseBooking, BookingDTO.class);

        return new ResponseEntity(responseMovieDTO, HttpStatus.OK );
    }


}
