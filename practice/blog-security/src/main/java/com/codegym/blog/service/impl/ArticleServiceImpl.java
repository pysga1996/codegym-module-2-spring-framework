package com.codegym.blog.service.impl;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import com.codegym.blog.repository.ArticleRepository;
import com.codegym.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void remove(Long id) {
        articleRepository.delete(id);
    }

    @Override
    public Iterable<Article> findAllByCategory(Category category){
        return articleRepository.findAllByCategory(category);
    }
}
