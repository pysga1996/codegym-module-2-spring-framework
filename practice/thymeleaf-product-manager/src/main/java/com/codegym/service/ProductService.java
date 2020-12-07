package com.codegym.service;

import com.codegym.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    void save(Product customer);

    Product findById(int id);

    List<Product> findByName(String name);

    void update(int id, Product customer);

    void remove(int id);
}
