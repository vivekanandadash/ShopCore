package com.order_service.mapper;

import com.order_service.dto.OrderDto;
import com.order_service.dto.OrderItemDto;
import com.order_service.entity.Order;
import com.order_service.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static OrderDto convertToDto(Order order){
        OrderDto dto = new OrderDto();

        dto.setId(order.getId());
        dto.setCartUuid(order.getCartUuid());
        dto.setUserId(order.getUserId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());

        List<OrderItemDto> orderItemDtos = new ArrayList<>();

        for (OrderItem item : order.getItems()){
            OrderItemDto itemDto = new OrderItemDto();
            itemDto.setId(item.getId());
            itemDto.setProductId(item.getProductId());
            itemDto.setBrandId(item.getBrandId());
            itemDto.setQuantity(item.getQuantity());
            itemDto.setPrice(item.getPrice());

            orderItemDtos.add(itemDto);

        }
        dto.setItems(orderItemDtos);
        return dto;

    }
}
