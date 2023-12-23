package com.EcommerceProjectModule.PaymentService.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "orders")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int  id;
        private int totalAmount;
        private String status;
        private String paymentMode;
        private String email;
        private String phone;
        private String address;
        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Cart cart;
        @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
        List<Product> products;
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private  Transaction transaction;
}
