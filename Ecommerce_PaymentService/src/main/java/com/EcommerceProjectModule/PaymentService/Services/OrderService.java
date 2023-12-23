package com.EcommerceProjectModule.PaymentService.Services;

import com.EcommerceProjectModule.PaymentService.DTOs.PlaceOrderRequestDTO;
import com.EcommerceProjectModule.PaymentService.Models.Cart;
import com.EcommerceProjectModule.PaymentService.Models.Order;
import com.EcommerceProjectModule.PaymentService.Models.Product;
import com.EcommerceProjectModule.PaymentService.Repositories.CartRepository;
import com.EcommerceProjectModule.PaymentService.Repositories.OrderRepository;
import com.EcommerceProjectModule.PaymentService.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public Order placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) {
        Order order = new Order();
        order.setPaymentMode(placeOrderRequestDTO.getPaymentMode());
        Cart cart = cartRepository.findById(placeOrderRequestDTO.getCartId()).get();
        order.setCart(cart);
        order.setTotalAmount(cart.getTotalAmount());
        order.setEmail(placeOrderRequestDTO.getEmail());
        order.setPhone(placeOrderRequestDTO.getPhone());
        order.setAddress(placeOrderRequestDTO.getAddress());
        order.setStatus("PENDING");
////        orderRepository.save(order);
//        for(int i = 0; i<cart.getProducts().size(); i++){
//            int id = cart.getProducts().get(i).getProductId();
//            Product product = productRepository.findById(id).get();
//            product.setOrder(order);
//            productRepository.save(product);
//        }
//        orderRepository.save(order);
        orderRepository.save(order);
        // Save the order first
        for (int i = 0; i < cart.getProducts().size(); i++) {
            int id = cart.getProducts().get(i).getProductId();
            Product product = productRepository.findById(id).get();
            product.setOrder(order);
            productRepository.save(product);
            // Do not save the product here, let it be saved when you save the order
        }
        // Update the products in the order

        // Save the order again to cascade the changes to products
        return order;

    }
}
