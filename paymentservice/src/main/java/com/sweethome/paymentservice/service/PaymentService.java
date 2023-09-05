package com.sweethome.paymentservice.service;

import com.sweethome.paymentservice.model.TransactionDetailsEntity;
import com.sweethome.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public TransactionDetailsEntity create(TransactionDetailsEntity transactionDetailsEntity) {
        return paymentRepository.save(transactionDetailsEntity);
    }

    public List<TransactionDetailsEntity> findAll() {
        List<TransactionDetailsEntity> payment = new ArrayList<>();
        paymentRepository.findAll().forEach(payment::add);

        return payment;
    }

    public Optional<TransactionDetailsEntity> findById(long id) {
        return paymentRepository.findById(id);
    }

    public TransactionDetailsEntity update(TransactionDetailsEntity paymentToUpdate) {
        return paymentRepository.save(paymentToUpdate);
    }

    public void delete(long id) {
        paymentRepository.deleteById(id);
    }
}
