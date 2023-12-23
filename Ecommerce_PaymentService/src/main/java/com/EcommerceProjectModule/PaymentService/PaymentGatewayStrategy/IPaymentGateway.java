package com.EcommerceProjectModule.PaymentService.PaymentGatewayStrategy;


import com.EcommerceProjectModule.PaymentService.Models.Order;
import org.springframework.stereotype.Service;

@Service
public interface IPaymentGateway {
    String generatePaymentLink(Order order);
}
