package com.codegym.blog.service;

import com.codegym.blog.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    Article findById(Long id);

    void save(Article article);

    void remove(Long id);
}
