package com.cart_service.mapper;

import com.cart_service.dto.CartDto;
import com.cart_service.dto.CartItemDto;
import com.cart_service.entity.Cart;
import com.cart_service.entity.CartItem;
import java.util.ArrayList;
import java.util.List;

public class CartMapper {

    public static CartDto convertCartToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUuid(cart.getUuid());
        cartDto.setUserId(cart.getUserId());

        // Normal for loop instead of stream
        List<CartItemDto> itemDtos = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            CartItemDto cartItemDto = convertCartItemToDto(cartItem);
            itemDtos.add(cartItemDto);
        }

        cartDto.setItems(itemDtos);
        return cartDto;
    }

    public static CartItemDto convertCartItemToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setBrandId(cartItem.getBrandId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPrice(cartItem.getPrice());
        return cartItemDto;
    }
}