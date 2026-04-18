package com.order_service.controller;

import com.order_service.dto.ApiResponse;
import com.order_service.dto.OrderDto;
import com.order_service.entity.Order;
import com.order_service.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final  OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/place")
    public ResponseEntity<ApiResponse<OrderDto>>placeOrder(@RequestHeader("X-CART-ID") String uuid){
        if (uuid == null || uuid.isBlank()) {
            throw new RuntimeException("UUID must not be empty");
        }
        OrderDto orderDto = orderService.placeOrder(uuid);
        ApiResponse<OrderDto> response = new ApiResponse<>();
            response.setMessage("Order placed successfully");
            response.setStatus(HttpStatus.CREATED.value());
            response.setData(orderDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
