package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;
import com.codegym.cms.service.CustomerService;
import com.codegym.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final ProvinceService provinceService;

    @Autowired
    public CustomerController(CustomerService customerService,
        ProvinceService provinceService) {
        this.customerService = customerService;
        this.provinceService = provinceService;
    }

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView listCustomers(@RequestParam(name = "word", required = false) String word,
        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
        @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
//      @PageableDefault(size = 10) Pageable pageable
        Page<Customer> customers;
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = new Sort(Sort.Direction.ASC, "firstName");
        }
        if (sort.equals("DESC")) {
            sortable = new Sort(Sort.Direction.DESC, "firstName");
        }
        Pageable pageable = new PageRequest(page, size, sortable);
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        if (word != null && !word.trim().isEmpty()) {
            customers = customerService.findAllByFirstNameContaining(word, pageable);
            modelAndView.addObject("matchesCount", customers.getTotalElements());

        } else {
            customers = customerService.findAll(pageable);
        }
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);

        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer);
            return modelAndView;

        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer,
        RedirectAttributes redirect) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer);
            return modelAndView;

        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/delete")
    public ModelAndView deleteCustomer(@ModelAttribute("customer") Customer customer,
        RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("message", "Delete customer successfully");
        return new ModelAndView("redirect:/customer/list");
    }
}