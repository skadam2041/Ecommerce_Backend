package com.EcommerceProjectModule.PaymentService.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class CallbackReqDTO {
    private String CHECKOUT_SESSION_ID;
}
