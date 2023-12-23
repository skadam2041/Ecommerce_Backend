package com.EcommerceProjectModule.PaymentService.Repositories;

import com.EcommerceProjectModule.PaymentService.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
