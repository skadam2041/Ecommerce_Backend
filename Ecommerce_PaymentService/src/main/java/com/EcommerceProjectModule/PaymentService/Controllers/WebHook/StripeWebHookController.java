package com.EcommerceProjectModule.PaymentService.Controllers.WebHook;


import com.EcommerceProjectModule.PaymentService.DTOs.CallbackReqDTO;
import com.EcommerceProjectModule.PaymentService.Services.PaymentService;
import com.stripe.Stripe;
import com.stripe.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripePayment")
public class StripeWebHookController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/getPaymentStatus")
    public String getPaymentStatus(@RequestBody Event paymentResponse){
        System.out.println( paymentResponse.toString());
        System.out.println("next -------------------------");
        return "paymentService.paymentCallback(paymentResponse)";
    }

    @GetMapping("/callbackStatus/{CHECKOUT_SESSION_ID}")
    public String getCallBackStatus(@PathVariable("CHECKOUT_SESSION_ID") String CHECKOUT_SESSION_ID){
        System.out.println("CHECKOUT_SESSION_ID");
        return "paymentService.paymentCallback(paymentResponse)";
    }
}

