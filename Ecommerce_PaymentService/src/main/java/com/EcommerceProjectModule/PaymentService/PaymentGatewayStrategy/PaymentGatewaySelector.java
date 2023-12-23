package com.EcommerceProjectModule.PaymentService.PaymentGatewayStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PaymentGatewaySelector {

    @Autowired
    private RazorpayPG razorpayPG;
    @Autowired
    private StripePG stripePG;

    public IPaymentGateway getPaymentGateway(String mode){
        if(mode.equals("razorpay"))
            return razorpayPG;
        else if(mode.equals("stripe"))
            return stripePG;
        else
            return null;
    }
}
