package com.order_service.service;

import com.cart_service.entity.CartItem;
import com.order_service.client.CartFeignClient;
import com.order_service.dto.CartDto;
import com.order_service.dto.CartItemDto;
import com.order_service.entity.Order;
import com.order_service.entity.OrderItem;
import com.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl {
    private final CartFeignClient cartFeignClient;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CartFeignClient cartFeignClient, OrderRepository orderRepository) {
        this.cartFeignClient = cartFeignClient;
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(String uuid){
        CartDto cart = cartFeignClient.getCart(uuid);
        if (cart == null || cart.getItems().isEmpty()){
            throw new RuntimeException("Cart is empty");
        }
        Order order = new Order();
        order.setCartUuid(uuid);
        order.setUserId(cart.getUserId());
        order.setStatus("CREATED");

        BigDecimal total = BigDecimal.ZERO;

        for (CartItemDto cartItemDto : cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItemDto.getProductId());
            orderItem.setBrandId(cartItemDto.getBrandId());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setPrice(cartItemDto.getPrice());
            orderItem.setOrder(order);
        }
        //working from here
        return null;
    }
}
