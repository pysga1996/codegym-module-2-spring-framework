package com.codegym.blog.controller;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import com.codegym.blog.service.ArticleService;
import com.codegym.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user/article")
public class ArticleController {

    private final ArticleService articleService;

    private final CategoryService categoryService;

    @Autowired
    public ArticleController(ArticleService articleService,
        CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView listArticles() {
        Pageable custom_pageable = new PageRequest(0, 2, Sort.Direction.DESC, "title");
        Page<Article> articles = articleService.findAll(custom_pageable);
        ModelAndView modelAndView = new ModelAndView("/article/list");
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView readArticle(@PathVariable Long id) {
        Article article = articleService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/article/view");
        modelAndView.addObject("article", article);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/article/create");
        modelAndView.addObject("article", new Article());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveArticle(@ModelAttribute("article") Article article) {
        articleService.save(article);
        ModelAndView modelAndView = new ModelAndView("/article/create");
        modelAndView.addObject("article", new Article());
        modelAndView.addObject("message", "New article created successfully");
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if (article != null) {
            ModelAndView modelAndView = new ModelAndView("/article/edit");
            modelAndView.addObject("article", article);
            return modelAndView;

        } else {
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateArticle(@ModelAttribute("article") Article article) {
        articleService.save(article);
        ModelAndView modelAndView = new ModelAndView("/article/edit");
        modelAndView.addObject("article", article);
        modelAndView.addObject("message", "Article updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if (article != null) {
            ModelAndView modelAndView = new ModelAndView("/article/delete");
            modelAndView.addObject("article", article);
            return modelAndView;

        } else {
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/delete")
    public String deleteArticle(@ModelAttribute("article") Article article) {
        articleService.remove(article.getId());
        return "redirect:/user/article/list";
    }
}
