package com.EcommerceProjectModule.PaymentService.Services;

import com.EcommerceProjectModule.PaymentService.DTOs.AddToCartRequestDTO;
import com.EcommerceProjectModule.PaymentService.Models.Cart;
import com.EcommerceProjectModule.PaymentService.Models.Product;
import com.EcommerceProjectModule.PaymentService.Repositories.CartRepository;
import com.EcommerceProjectModule.PaymentService.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private ProductRepository productRepository;


    public Cart addToCart(AddToCartRequestDTO addToCartRequestDTO) {
        Cart cart = null;
        try {
            cart = cartRepository.findById(addToCartRequestDTO.getCartId()).get();
            Product product = fetchProduct(addToCartRequestDTO.getProductId());
            List<Product> productsList = cart.getProducts();
            productsList.add(product);
            cart.setProducts(productsList);
            cart.setTotalAmount(cart.getTotalAmount() + product.getPrice());
            product.setCart(cart);
            productRepository.save(product);
        }
        catch(Exception e){
            if (cart == null) {
                cart = new Cart();
                cart.setUserId(addToCartRequestDTO.getUserId());
                cart.setStatus("Active");
                cart.setId(addToCartRequestDTO.getCartId());
                Product product = fetchProduct(addToCartRequestDTO.getProductId());
                List<Product> productsList = new ArrayList<>();
                productsList.add(product);
                cart.setProducts(productsList);
                cart.setTotalAmount(product.getPrice());
                product.setCart(cart);
                productRepository.save(product);
            }
        }
        return cart;
    }

    private Product fetchProduct(int productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        Product product = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", Product.class, productId).getBody();
        return product;
    }

    public void removeFromCart(AddToCartRequestDTO addToCartRequestDTO) {
        Cart cart = cartRepository.findById(addToCartRequestDTO.getCartId()).get();
        Product product = fetchProduct(addToCartRequestDTO.getProductId());
        List<Product> productsList = cart.getProducts();
        productsList.remove(product);
        cart.setProducts(productsList);
        cart.setTotalAmount(cart.getTotalAmount() - product.getPrice());
        product.setCart(null);
        productRepository.save(product);
    }
}
