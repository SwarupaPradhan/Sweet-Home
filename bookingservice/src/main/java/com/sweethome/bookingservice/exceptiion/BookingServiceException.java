package com.sweethome.bookingservice.exceptiion;

public class BookingServiceException extends RuntimeException{

    private final int statusCode;

    public BookingServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }



}
