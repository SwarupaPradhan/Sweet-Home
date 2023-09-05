package com.sweethome.bookingservice.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private String paymentMode;
    private int bookingId;
    private String upiId;
    private String cardNumber;


}
