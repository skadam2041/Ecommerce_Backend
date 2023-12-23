package com.EcommerceProjectModule.PaymentService.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequestDTO {
    private  int userId;
    private int cartId;
    private int productId;
}
