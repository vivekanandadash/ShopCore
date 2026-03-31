package com.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
@Data
public class BrandDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long productId;
    private Set<SizeDto> sizes;
    private Set<ImageDto> images;

}
