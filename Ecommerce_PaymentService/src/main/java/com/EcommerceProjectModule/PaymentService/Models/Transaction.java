package com.EcommerceProjectModule.PaymentService.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "transaction")
public class Transaction extends BaseModel{
    private int amount;
    @OneToOne(mappedBy = "transaction",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Order order;
    private String transactionId ;
    private Date date;
    private String status;
    private String paymentMode;
}
