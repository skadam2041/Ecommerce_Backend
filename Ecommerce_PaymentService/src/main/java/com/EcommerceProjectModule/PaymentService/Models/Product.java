package com.EcommerceProjectModule.PaymentService.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@Entity(name = "product")
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int productId;
        private int id;
        private String title;
        private int price;
        private int quantity = 1;
        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @Fetch(FetchMode.SELECT)
        private Cart cart;
        @ManyToOne(fetch = FetchType.LAZY)
        private Order order;
}
