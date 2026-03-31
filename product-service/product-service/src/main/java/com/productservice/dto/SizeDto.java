package com.productservice.dto;
import lombok.Data;

@Data
public class SizeDto {
    private Long id;
    private String size;
    private String quantity;
    private Long brandId;
}
