package com.codegym.blog.controller;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import com.codegym.blog.service.ArticleService;
import com.codegym.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final ArticleService articleService;

    @Autowired
    public CategoryController(CategoryService categoryService,
        ArticleService articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> listAllCustomers() {
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<List<Category>>(
                HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Category> viewCategory(@PathVariable("id") Long id) {
//        Category category = categoryService.findById(id);
//        if(category == null){
//            return new ModelAndView("/error.404");
//        }
//        Iterable<Article> articles = articleService.findAllByCategory(category);
//        ModelAndView modelAndView = new ModelAndView("/category/view");
//        modelAndView.addObject("category", category);
//        modelAndView.addObject("articles", articles);
//        return modelAndView;

        System.out.println("Fetching Category with id " + id);
        Category category = categoryService.findById(id);
        List<Article> articles = articleService.findAllByCategory(category);
        if (category == null) {
            System.out.println("Category with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        category.setArticles(articles);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCategory(@RequestBody Category category,
        UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Category " + category.getName());
        categoryService.save(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/view/{id}").buildAndExpand(category.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/view/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id,
        @RequestBody Category category) {
        System.out.println("Updating Category " + id);
        Category currentCategory = categoryService.findById(id);
        if (currentCategory == null) {
            System.out.println("Category with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        currentCategory.setId(category.getId());
        currentCategory.setName(category.getName());
        categoryService.save(currentCategory);
        return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
    }

    @DeleteMapping("/view/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Category with id " + id);
        Category category = categoryService.findById(id);
        if (category == null) {
            System.out.println("Unable to delete. Category with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
