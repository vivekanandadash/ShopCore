package com.productservice.service;

import com.productservice.dto.CategoryDto;
import com.productservice.entity.Category;
import com.productservice.mapper.CategoryMapper;
import com.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
       List<CategoryDto> dtoList = new ArrayList<>();
       for (Category category:categories){
           CategoryDto categoryDto = CategoryMapper.convertCategoryToDto(category);
           dtoList.add(categoryDto);
       }
        return dtoList;
    }

    @Override
    public CategoryDto findByCategoryId(Long id) {
        return null;
    }

    @Override
    public CategoryDto findByCategoryName(String name) {
        return null;
    }
}
