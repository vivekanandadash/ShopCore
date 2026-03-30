package com.productservice.repository;

import com.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
    SELECT DISTINCT p FROM Product p
    LEFT JOIN p.brands b
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.subCategory.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Product> searchProducts(@Param("keyword") String keyword);
}