package com.productservice.controller;

import com.productservice.dto.ApiResponse;
import com.productservice.dto.CategoryDto;
import com.productservice.dto.ProductDto;
import com.productservice.service.CategoryService;
import com.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
 // Constructor Based Dependency Injection
    private CategoryService categoryService;
    private ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }
    //Get Mapping For  get the all data
    @GetMapping("/list/categories")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getCategories(){
        List<CategoryDto> categoriesDto = categoryService.findAll();
        ApiResponse<List<CategoryDto>> response = new ApiResponse<>();
        if (categoriesDto!=null && !categoriesDto.isEmpty()){
            response.setMessage("All Categories Data fetched");
            response.setStatus(200);
            response.setData(categoriesDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setMessage("No Categories Data Found");
        response.setStatus(500);
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/list/search")
    public ResponseEntity<ApiResponse<List<ProductDto>>> searchProducts(
            @RequestParam String keyword
    ){
        List<ProductDto> productsDto = productService.searchProducts(keyword);
        ApiResponse<List<ProductDto>> response = new ApiResponse<>();
        if (productsDto!=null && !productsDto.isEmpty()){
            response.setMessage("All  Data fetched");
            response.setStatus(200);
            response.setData(productsDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setMessage("No Matching Record Found");
        response.setStatus(500);
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
