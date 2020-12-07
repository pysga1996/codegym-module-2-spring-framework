package com.codegym.blog.controller;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import com.codegym.blog.service.ArticleService;
import com.codegym.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/category")
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
    public ModelAndView listCategories(){
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if(category == null){
            return new ModelAndView("/error-404");
        }

        Iterable<Article> articles = articleService.findAllByCategory(category);

        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);

        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if(category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category", category);
            return modelAndView;

        } else {
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if(category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category);
            return modelAndView;

        } else {
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/delete")
    public String deleteCategory(@ModelAttribute("category") Category category){
        categoryService.remove(category.getId());
        return "redirect:/user/category/list";
    }
}
