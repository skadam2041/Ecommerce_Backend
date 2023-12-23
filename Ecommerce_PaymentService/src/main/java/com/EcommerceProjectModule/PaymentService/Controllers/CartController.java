package com.EcommerceProjectModule.PaymentService.Controllers;


import com.EcommerceProjectModule.PaymentService.DTOs.AddToCartRequestDTO;
import com.EcommerceProjectModule.PaymentService.DTOs.AddedToCartResponceDTO;
import com.EcommerceProjectModule.PaymentService.Models.Cart;
import com.EcommerceProjectModule.PaymentService.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<AddedToCartResponceDTO>  addProductToCart(@RequestBody AddToCartRequestDTO  addToCartRequestDTO){
        Cart cart  =  cartService.addToCart(addToCartRequestDTO);
        AddedToCartResponceDTO  addedToCartResponceDTO = new AddedToCartResponceDTO();
        addedToCartResponceDTO.setCartId(cart.getId());
        addedToCartResponceDTO.setMsg(cart.getUserId() + "  " + "added product to cart");
        addedToCartResponceDTO.setProductName(cart.getProducts().get(cart.getProducts().size()-1).getTitle());
        addedToCartResponceDTO.setPrice(cart.getTotalAmount());
        addedToCartResponceDTO.setQuantity(cart.getProducts().get(cart.getProducts().size()-1).getQuantity());
        return new ResponseEntity<AddedToCartResponceDTO>(addedToCartResponceDTO, HttpStatus.OK);
    }


    @PostMapping("/removeFromCart")
    public ResponseEntity<String> removeProductFromCart(@RequestBody AddToCartRequestDTO  addToCartRequestDTO){
        cartService.removeFromCart(addToCartRequestDTO);
        return new ResponseEntity<String>("Product removed from cart",HttpStatus.OK);
    }

}
