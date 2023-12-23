package com.EcommerceProjectModule.PaymentService.Controllers;

import com.EcommerceProjectModule.PaymentService.DTOs.PlaceOrderRequestDTO;
import com.EcommerceProjectModule.PaymentService.DTOs.PlacedOrderResponceDTO;
import com.EcommerceProjectModule.PaymentService.Models.Order;
import com.EcommerceProjectModule.PaymentService.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public PlacedOrderResponceDTO placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO){
        Order order = orderService.placeOrder(placeOrderRequestDTO);
        PlacedOrderResponceDTO placedOrderResponceDTO = new PlacedOrderResponceDTO();
        placedOrderResponceDTO.setOrderId(order.getId());
        placedOrderResponceDTO.setStatus("Order Placed Successfully" + order.getStatus());
        placedOrderResponceDTO.setAmount(order.getTotalAmount());
        return placedOrderResponceDTO;
    }

}
