package com.sweethome.paymentservice.repository;

import com.sweethome.paymentservice.model.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<TransactionDetailsEntity, Long> {

}
