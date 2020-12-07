package com.codegym.ecommerce.service.impl;

import com.codegym.ecommerce.model.Product;
import com.codegym.ecommerce.repository.ProductRepository;
import com.codegym.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Page<Product> findAllByNameContaining(String s, Pageable pageable) {
        return productRepository.findAllByNameContaining(s, pageable);
    }

}
