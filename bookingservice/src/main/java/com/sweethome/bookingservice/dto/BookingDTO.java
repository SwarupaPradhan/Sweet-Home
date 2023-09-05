package com.sweethome.bookingservice.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class BookingDTO {

    private int bookingId;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String aadharNumber;

    private int numOfRooms;

    private ArrayList<String> roomNumbers;

    private int roomPrice;

    private int transactionId;

    private LocalDate bookedOn;

}




