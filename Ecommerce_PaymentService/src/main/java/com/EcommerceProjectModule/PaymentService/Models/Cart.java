package com.EcommerceProjectModule.PaymentService.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Transaction;

import java.util.List;



@Getter
@Setter
@Entity(name = "cart")
public class Cart extends BaseModel{

    private int userId;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
    private int totalAmount;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> orders;
    private String status;

}
