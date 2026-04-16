package com.cart_service.controller;

import com.cart_service.dto.AddToCartRequestDto;
import com.cart_service.dto.ApiResponse;
import com.cart_service.dto.CartDto;
import com.cart_service.entity.Cart;
import com.cart_service.service.CartService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(exposedHeaders = "X-CART-ID")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/add")
   public ResponseEntity<ApiResponse<CartDto>> addToCart(
            @RequestHeader(value = "X-CART-ID", required = false) String uuid,
            @RequestBody AddToCartRequestDto addToCartRequestDto
    ){
        CartDto cartDto = cartService.addToCart(uuid, addToCartRequestDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-CART-ID",cartDto.getUuid());

        ApiResponse<CartDto> response = new ApiResponse<>();
        response.setMessage("product add in the bag");
        response.setStatus(200);
        response.setData(cartDto);
        return new ResponseEntity<>(response,httpHeaders, HttpStatus.OK);

    }

    // FeignClient calls this method from Order service to get the uuid!
    @GetMapping("/{uuid}")
    public ResponseEntity<CartDto> getCart(@PathVariable String uuid) {
        CartDto cartDto = cartService.getCartByUuid(uuid);
        return ResponseEntity.ok(cartDto);
    }
    // FeignClient calls this method from Order service to delete the uuid!
    @DeleteMapping("/{uuid}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable String uuid) {
        cartService.clearCart(uuid);
        return ResponseEntity.noContent().build();
    }

}
