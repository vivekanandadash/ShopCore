package com.cart_service.service;

import com.cart_service.dto.AddToCartRequestDto;
import com.cart_service.entity.Cart;
import com.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public Cart addToCart(String uuid, AddToCartRequestDto addToCartRequestDto){
        Cart cart;

        //Check if uuid is present or not ?
        if (uuid == null || uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString();
            cart = new Cart();
            cart.setUuid(uuid);
        }else {
            Optional<Cart> optionalCart = cartRepository.findByUuid(uuid);

            if (optionalCart.isPresent()) {
                cart = optionalCart.get();
            } else {
                cart = new Cart();
                cart.setUuid(uuid);
            }
        }
        return null;

    }
}
