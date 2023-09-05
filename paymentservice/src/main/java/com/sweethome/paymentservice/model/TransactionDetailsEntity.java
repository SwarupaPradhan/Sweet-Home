package com.sweethome.paymentservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transcationId;
    @Enumerated(EnumType.STRING)
    private paymentMode paymentMode;

    public enum paymentMode{
        UPI,
        CARD
    }

    @Column(nullable = false)
    private int bookingId;

    private String upiId;

    private String cardNumber;

    public long getTranscationId(){
        return transcationId;
    }

    public void setTranscationId(int transcationId){
        this.transcationId = transcationId;
    }

    public paymentMode getPaymentMode(){
        return paymentMode;
    }

    public void setPaymentMode(paymentMode paymentMode){
        this.paymentMode = paymentMode;
    }

    public int getBookingId(){
        return bookingId;
    }

    public void setBookingId(int bookingId){
        this.bookingId = bookingId;
    }

    public String getUpiId(){
        return upiId;
    }

    public void setUpiId(String upiId){
        this.upiId = upiId;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "TransactionDetailsEntity{" +
                "bookingId=" + bookingId +
                ", transactionId=" + transcationId +
                ", upiId=" + upiId +
                ", paymentMode=" + paymentMode +
                ", cardNumber=" + cardNumber +
                '}';
    }


}
