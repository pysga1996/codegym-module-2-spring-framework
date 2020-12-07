package com.codegym.controller.common;

import com.codegym.model.Category;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "", params = "action=list")
    public ModelAndView list(Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        return new ModelAndView("/category/list", "categories", categories);
    }

    @GetMapping(value = "", params = {"action=view", "id"})
    public ModelAndView readCategory(Long id) {
        Category category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        return modelAndView;
    }


    @GetMapping(value = "", params = "action=create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping(value = "", params = "action=create")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }


    @GetMapping(value = "", params = {"action=edit", "id"})
    public ModelAndView showEditForm(Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category", category);
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping(value = "", params = "action=edit")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }

    @GetMapping(value = "", params = {"action=delete", "id"})
    public ModelAndView showDeleteForm(Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category);
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping(value = "", params = "action=delete")
    public String deleteArticle(@ModelAttribute("category") Category category) {
        categoryService.delete(category.getId());
        return "redirect:/user/category/list";
    }
}
