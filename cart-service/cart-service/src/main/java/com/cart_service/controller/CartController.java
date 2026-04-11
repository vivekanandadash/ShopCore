package com.cart_service.controller;

import com.cart_service.dto.AddToCartRequestDto;
import com.cart_service.dto.ApiResponse;
import com.cart_service.entity.Cart;
import com.cart_service.service.CartService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
   public ResponseEntity<ApiResponse<Cart>> addToCart(
            @RequestHeader(value = "X-CART-ID", required = false) String uuid,
            @RequestBody AddToCartRequestDto addToCartRequestDto
    ){
        Cart cart = cartService.addToCart(uuid, addToCartRequestDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-CART-ID",cart.getUuid());
        ApiResponse<Cart> response = new ApiResponse<>();
        response.setMessage("product add in the bag");
        response.setStatus(200);
        response.setData(cart);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
