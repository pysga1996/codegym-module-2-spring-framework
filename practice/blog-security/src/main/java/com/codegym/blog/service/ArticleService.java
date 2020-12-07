package com.codegym.blog.service;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    Page<Article> findAll(Pageable pageable);

    Article findById(Long id);

    void save(Article article);

    void remove(Long id);

    Iterable<Article> findAllByCategory(Category category);
}
