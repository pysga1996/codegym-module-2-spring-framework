package com.codegym.blog.controller;

import com.codegym.blog.model.Article;
import com.codegym.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public ModelAndView listCustomers() {
        List<Article> articles = articleService.findAll();
        ModelAndView modelAndView = new ModelAndView("/article/list");
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @GetMapping("/read-article/{id}")
    public ModelAndView readArticle(@PathVariable Long id) {
        Article article = articleService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/article/read");
        modelAndView.addObject("article", article);
        return modelAndView;
    }


    @GetMapping("/create-article")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/article/create");
        modelAndView.addObject("article", new Article());
        return modelAndView;
    }

    @PostMapping("/create-article")
    public ModelAndView saveCustomer(@ModelAttribute("article") Article article) {
        articleService.save(article);
        ModelAndView modelAndView = new ModelAndView("/article/create");
        modelAndView.addObject("article", new Article());
        modelAndView.addObject("message", "New article created successfully");
        return modelAndView;
    }


    @GetMapping("/edit-article/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if (article != null) {
            ModelAndView modelAndView = new ModelAndView("/article/edit");
            modelAndView.addObject("article", article);
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-article")
    public ModelAndView updateCustomer(@ModelAttribute("article") Article article) {
        articleService.save(article);
        ModelAndView modelAndView = new ModelAndView("/article/edit");
        modelAndView.addObject("article", article);
        modelAndView.addObject("message", "Article updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-article/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if (article != null) {
            ModelAndView modelAndView = new ModelAndView("/article/delete");
            modelAndView.addObject("article", article);
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/delete-article")
    public String deleteCustomer(@ModelAttribute("article") Article article) {
        articleService.remove(article.getId());
        return "redirect:articles";
    }
}
