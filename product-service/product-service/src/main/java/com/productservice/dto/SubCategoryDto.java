package com.productservice.dto;

import lombok.Data;

@Data
public class SubCategoryDto {
    private Long id;
    private String name;
    private Long categoryId;
}
