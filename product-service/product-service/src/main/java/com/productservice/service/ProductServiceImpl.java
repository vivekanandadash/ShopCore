package com.productservice.service;

import com.productservice.dto.ProductDto;
import com.productservice.entity.Product;
import com.productservice.mapper.ProductMapper;
import com.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> searchProducts(String keyword) {
        List<Product> products = productRepository.searchProducts(keyword);
       List<ProductDto> productDtos = new ArrayList<>();
       for (Product product : products){
//           ProductDto productDto = ProductMapper.convertProductToDto(product);
//           productDtos.add(productDto);

//           Note:- Both Commented line and bellow are same i, just optimize the code
           productDtos.add( ProductMapper.convertProductToDto(product));
       }
        return productDtos;
    }
}
