package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    // ==========================================
    // Service
    // ==========================================

    private final CustomerService customerService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;

    }

    // ==========================================
    // Customer List
    // ==========================================

    @GetMapping
    public String listCustomers(Model model) {

        model.addAttribute("customers",
                customerService.getAllCustomers());

        model.addAttribute("totalCustomers",
                customerService.getTotalCustomers());

        model.addAttribute("activeCustomers",
                customerService.getActiveCustomers());

        model.addAttribute("businessCustomers",
                customerService.getBusinessCustomers());

        model.addAttribute("individualCustomers",
                customerService.getIndividualCustomers());

        return "customer/list";

    }

    // ==========================================
    // Add Customer Page
    // ==========================================

    @GetMapping("/add")
    public String addCustomerForm(Model model) {

        model.addAttribute("customer", new Customer());

        return "customer/add";

    }

    // ==========================================
    // Save Customer
    // ==========================================

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer) {

        customerService.saveCustomer(customer);

        return "redirect:/customers";

    }

    // ==========================================
    // View Customer
    // ==========================================

    @GetMapping("/view/{id}")
    public String viewCustomer(@PathVariable Long id,
                               Model model) {

        model.addAttribute("customer",
                customerService.getCustomerById(id));

        return "customer/view";

    }
    // ==========================================
    // Edit Customer Page
    // ==========================================

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id,
                               Model model) {

        model.addAttribute("customer",
                customerService.getCustomerById(id));

        return "customer/edit";

    }

    // ==========================================
    // Update Customer
    // ==========================================

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer) {

        customerService.updateCustomer(customer);

        return "redirect:/customers";

    }

    // ==========================================
    // Delete Customer
    // ==========================================

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);

        return "redirect:/customers";

    }

    // ==========================================
    // Search Customer By Customer ID
    // ==========================================

    @GetMapping("/customer-id/{customerId}")
    @ResponseBody
    public Customer getCustomerByCustomerId(
            @PathVariable String customerId) {

        return customerService.getCustomerByCustomerId(customerId);

    }

    // ==========================================
    // Search Customer By Email
    // ==========================================

    @GetMapping("/email/{email}")
    @ResponseBody
    public Customer getCustomerByEmail(
            @PathVariable String email) {

        return customerService.getCustomerByEmail(email);

    }

    // ==========================================
    // Search Customer By Phone
    // ==========================================

    @GetMapping("/phone/{phone}")
    @ResponseBody
    public Customer getCustomerByPhone(
            @PathVariable String phone) {

        return customerService.getCustomerByPhone(phone);

    }
    // ==========================================
    // Get Customers By City
    // ==========================================

    @GetMapping("/city/{city}")
    @ResponseBody
    public List<Customer> getCustomersByCity(
            @PathVariable String city) {

        return customerService.getCustomersByCity(city);

    }

    // ==========================================
    // Get Customers By State
    // ==========================================

    @GetMapping("/state/{state}")
    @ResponseBody
    public List<Customer> getCustomersByState(
            @PathVariable String state) {

        return customerService.getCustomersByState(state);

    }

    // ==========================================
    // Get Customers By Customer Type
    // ==========================================

    @GetMapping("/type/{customerType}")
    @ResponseBody
    public List<Customer> getCustomersByCustomerType(
            @PathVariable String customerType) {

        return customerService.getCustomersByCustomerType(customerType);

    }

    // ==========================================
    // Get Customers By Business Category
    // ==========================================

    @GetMapping("/category/{businessCategory}")
    @ResponseBody
    public List<Customer> getCustomersByBusinessCategory(
            @PathVariable String businessCategory) {

        return customerService.getCustomersByBusinessCategory(businessCategory);

    }

    // ==========================================
    // Get Customers By Status
    // ==========================================

    @GetMapping("/status/{status}")
    @ResponseBody
    public List<Customer> getCustomersByStatus(
            @PathVariable String status) {

        return customerService.getCustomersByStatus(status);

    }

}