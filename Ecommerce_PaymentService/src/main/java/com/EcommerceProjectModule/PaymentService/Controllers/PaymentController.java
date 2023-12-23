package com.EcommerceProjectModule.PaymentService.Controllers;

import com.EcommerceProjectModule.PaymentService.DTOs.InitiatePaymentRequestDTO;
import com.EcommerceProjectModule.PaymentService.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @PostMapping("/getPaymentLink")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDTO initiatePaymentRequestDTO){
        return paymentService.initiatePayment(initiatePaymentRequestDTO);
    }

}
