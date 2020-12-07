package com.codegym.blog.repository;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
    Iterable<Article> findAllByCategory(Category category);
}
