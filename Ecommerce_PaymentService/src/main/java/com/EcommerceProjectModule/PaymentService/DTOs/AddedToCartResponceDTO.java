package com.EcommerceProjectModule.PaymentService.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddedToCartResponceDTO{
    private String msg;
    private int cartId;
    private String productName;
    private int price;
    private int quantity;
}
