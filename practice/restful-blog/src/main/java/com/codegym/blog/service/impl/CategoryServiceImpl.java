package com.codegym.blog.service.impl;

import com.codegym.blog.model.Category;
import com.codegym.blog.repository.CategoryRepository;
import com.codegym.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id){
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category){
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id){
        categoryRepository.remove(id);
    }
}
