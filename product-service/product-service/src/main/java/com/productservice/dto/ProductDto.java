package com.productservice.dto;


import lombok.Data;
import java.util.Set;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private Long subCategoryId;
    private Set<BrandDto> brands;

}
