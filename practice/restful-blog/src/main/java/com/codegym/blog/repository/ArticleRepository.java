package com.codegym.blog.repository;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import java.util.List;

public interface ArticleRepository extends Repository<Article> {
    List<Article> findAllByCategory(Category category);
}
