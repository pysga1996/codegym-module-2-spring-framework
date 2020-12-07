package com.codegym.service.impl;

import com.codegym.model.Category;
import com.codegym.repository.CategoryRepository;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Category> findAll(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Category findById(Long id){
        return categoryRepository.findOne(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void save(Category category){
        categoryRepository.save(category);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id){
        categoryRepository.delete(id);
    }
}
