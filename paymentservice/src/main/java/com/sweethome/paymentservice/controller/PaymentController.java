package com.sweethome.paymentservice.controller;

import com.sweethome.paymentservice.model.TransactionDetailsEntity;
import com.sweethome.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentRepository paymentRepository;

    @PostMapping("/transaction")
    public ResponseEntity<Long> performTransaction(@RequestBody TransactionDetailsEntity paymentRequest) {
        try {
            paymentRepository.save(paymentRequest);
            // Print confirmation message on the console
            System.out.println("Booking confirmed for user with aadhaar number: "
                    + paymentRequest.getCardNumber()
                    + "    |    "
                    + "Here are the booking details:    " + paymentRequest.getUpiId());

            return new ResponseEntity<>(paymentRequest.getTranscationId(), HttpStatus.CREATED);
        } catch (Exception ex) {
            System.out.print(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionDetailsEntity> getTransactionDetails(@PathVariable long id) {
        try {
            Optional<TransactionDetailsEntity> transactionDetails = paymentRepository.findById(id);
            if (transactionDetails.isPresent()) {
                return new ResponseEntity<>(transactionDetails.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            System.out.print(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
