package com.codegym.blog.service.impl;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import com.codegym.blog.repository.ArticleRepository;
import com.codegym.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void remove(Long id) {
        articleRepository.remove(id);
    }

    @Override
    public List<Article> findAllByCategory(Category category){
        return articleRepository.findAllByCategory(category);
    }
}
