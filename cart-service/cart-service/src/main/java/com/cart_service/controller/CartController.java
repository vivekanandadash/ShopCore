package com.cart_service.controller;

import com.cart_service.dto.AddToCartRequestDto;
import com.cart_service.dto.ApiResponse;
import com.cart_service.entity.Cart;
import com.cart_service.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/add")
   public ResponseEntity<ApiResponse<String>> addToCart(
            @RequestHeader(value = "X-CART-ID", required = false) String uuid,
            @RequestBody AddToCartRequestDto addToCartRequestDto
    ){
        Cart cart = cartService.addToCart(uuid, addToCartRequestDto);
        //till above i created logic from tomorrow morning I will  proceed further
        return null;
    }

}
