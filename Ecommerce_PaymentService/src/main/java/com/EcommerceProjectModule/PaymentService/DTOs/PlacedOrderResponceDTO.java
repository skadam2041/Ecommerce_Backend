package com.EcommerceProjectModule.PaymentService.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlacedOrderResponceDTO {
    private int orderId;
    private int amount;
    private String status;
}
