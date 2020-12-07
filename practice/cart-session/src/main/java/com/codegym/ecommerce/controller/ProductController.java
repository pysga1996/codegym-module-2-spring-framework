package com.codegym.ecommerce.controller;

import com.codegym.ecommerce.model.Cart;
import com.codegym.ecommerce.model.Product;
import com.codegym.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/list")
    public ModelAndView listCustomers(@RequestParam(name = "s", required = false) String s,
        @PageableDefault(size = 5) Pageable pageable) {
        Page<Product> products;
        if (s != null && !s.trim().isEmpty()) {
            products = productService.findAllByNameContaining(s, pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;

        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Product updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:/product/list";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/view");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public ModelAndView addToCart(@PathVariable Long id, @ModelAttribute("cart") Cart cart,
        @RequestParam("action") String action, Pageable pageable) {
        ModelAndView modelAndView;
        Product product = productService.findById(id);
        if (action.equals("show")) {
            modelAndView = new ModelAndView("/cart/show");
        } else {
            Page<Product> products = productService.findAll(pageable);
            modelAndView = new ModelAndView("/product/list", "products", products);
        }
        cart.addProduct(product);
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeFromCart(@PathVariable Long id, @ModelAttribute("cart") Cart cart,
        @RequestParam("action") String action, Pageable pageable) {
        ModelAndView modelAndView;
        Product product = productService.findById(id);
        if (action.equals("show")) {
            modelAndView = new ModelAndView("/cart/show");
        } else {
            Page<Product> products = productService.findAll(pageable);
            modelAndView = new ModelAndView("/product/list", "products", products);
        }
        cart.removeProduct(product);
        return modelAndView;
    }
}
