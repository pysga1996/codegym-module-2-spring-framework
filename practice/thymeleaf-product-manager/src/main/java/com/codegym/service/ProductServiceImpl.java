package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private static final Map<Integer, Product> products;

    static {

        products = new HashMap<>();
        products.put(1, new Product(1, "Nintendo Switch", 500, ""));
        products.put(2, new Product(2, "Nintendo New 3DS", 300, ""));
        products.put(3, new Product(3, "Sony Playsation 4", 400, ""));
        products.put(4, new Product(4, "Sony Playstation Vita", 200, ""));
        products.put(5, new Product(5, "Microsoft Xbox 360", 500, ""));
        products.put(6, new Product(6, "Microsoft Xbox One", 600, ""));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {

        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public List<Product> findByName(String name) {
        ArrayList<Product> filteredProduct = new ArrayList<>();
        products.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredProduct.add(v);
            }
        });
        return filteredProduct;
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);

    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}
