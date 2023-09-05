package com.sweethome.bookingservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Data
@Entity()
@Table(name = "booking")
public class BookingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @Column(nullable = true)
    private LocalDate fromDate;

    @Column(nullable = true)
    private LocalDate toDate;

    @Column(length =50, nullable = true, unique = true)
    private String aadharNumber;

    private int numOfRooms;

    private ArrayList<String> roomNumbers;
    @Column(nullable = false)
    private int roomPrice;

    @Column(columnDefinition = "integer default 0")
    private int transactionId;

    @Column(nullable = true)
    private LocalDate bookedOn;



    public  ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }

}
