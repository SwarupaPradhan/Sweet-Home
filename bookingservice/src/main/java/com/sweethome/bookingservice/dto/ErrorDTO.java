package com.sweethome.bookingservice.dto;

public class ErrorDTO {

    private String message;
    private int statusCode;

    public ErrorDTO(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
