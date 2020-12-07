package com.codegym.ecommerce.service;

import com.codegym.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    void save(Product article);

    void remove(Long id);

    Page<Product> findAllByNameContaining(String s, Pageable pageable);
}
