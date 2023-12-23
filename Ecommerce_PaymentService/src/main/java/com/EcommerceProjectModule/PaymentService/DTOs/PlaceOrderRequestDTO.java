package com.EcommerceProjectModule.PaymentService.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderRequestDTO {
    private int cartId;
    private String paymentMode;
    private String email;
    private String phone;
    private String address;

}
