package com.codegym.ecommerce.repository;

import com.codegym.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findAllByNameContaining(String s, Pageable pageable);
}
