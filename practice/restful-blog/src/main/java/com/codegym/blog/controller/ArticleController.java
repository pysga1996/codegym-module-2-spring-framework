package com.codegym.blog.controller;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import com.codegym.blog.service.ArticleService;
import com.codegym.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/article")
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
    public ResponseEntity<Iterable<Category>> categories() {
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<Iterable<Category>>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Article>> listAllArticles() {
        List<Article> articles = articleService.findAll();
        if (articles.isEmpty()) {
            return new ResponseEntity<List<Article>>(
                HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Article> readArticle(@PathVariable("id") Long id) {
        System.out.println("Fetching Article with id " + id);
        Article article = articleService.findById(id);
        if (article == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> saveArticle(@RequestBody Article article,
        UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Article " + article.getTitle());
        articleService.save(article);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
            ucBuilder.path("/article/view/{id}").buildAndExpand(article.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Article> updateCustomer(@PathVariable("id") long id,
        @RequestBody Article article) {
        System.out.println("Updating Article " + id);
        Article currentArticle = articleService.findById(id);
        if (currentArticle == null) {
            System.out.println("Article with id " + id + " not found");
            return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
        }
        currentArticle.setId(article.getId());
        currentArticle.setTitle(article.getTitle());
        currentArticle.setCategory(article.getCategory());
        currentArticle.setAuthor(article.getAuthor());
        currentArticle.setPublishDate(article.getPublishDate());
        currentArticle.setContent(article.getContent());
        articleService.save(currentArticle);
        return new ResponseEntity<Article>(currentArticle, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Article> deleteCustomer(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Article with id " + id);
        Article article = articleService.findById(id);
        if (article == null) {
            System.out.println("Unable to delete. Article with id " + id + " not found");
            return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
        }
        articleService.remove(id);
        return new ResponseEntity<Article>(HttpStatus.NO_CONTENT);
    }
}
