package com.EcommerceProjectModule.PaymentService.Services;

import com.EcommerceProjectModule.PaymentService.DTOs.InitiatePaymentRequestDTO;
import com.EcommerceProjectModule.PaymentService.Models.Order;
import com.EcommerceProjectModule.PaymentService.PaymentGatewayStrategy.IPaymentGateway;
import com.EcommerceProjectModule.PaymentService.PaymentGatewayStrategy.PaymentGatewaySelector;
import com.EcommerceProjectModule.PaymentService.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentGatewaySelector paymentGatewaySelector;
    public String initiatePayment(InitiatePaymentRequestDTO initiatePaymentRequestDTO) {

        Order order = orderRepository.findById(initiatePaymentRequestDTO.getOrderId()).get();


        IPaymentGateway paymentGateway = paymentGatewaySelector.getPaymentGateway(order.getPaymentMode());

        return paymentGateway.generatePaymentLink(order);
    }

}
