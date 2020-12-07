package com.codegym.blog.service;

import com.codegym.blog.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);
}
