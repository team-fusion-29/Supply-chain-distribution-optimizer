package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Customer;

public interface CustomerService {

    // ==========================================
    // CRUD Operations
    // ==========================================

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long id);

    // ==========================================
    // Search Operations
    // ==========================================

    Customer getCustomerByCustomerId(String customerId);

    Customer getCustomerByEmail(String email);

    Customer getCustomerByPhone(String phone);

    List<Customer> getCustomersByCompanyName(String companyName);

    List<Customer> getCustomersByCity(String city);

    List<Customer> getCustomersByState(String state);

    List<Customer> getCustomersByCustomerType(String customerType);

    List<Customer> getCustomersByBusinessCategory(String businessCategory);

    List<Customer> getCustomersByStatus(String status);

    // ==========================================
    // Exists Methods
    // ==========================================

    boolean customerIdExists(String customerId);

    boolean emailExists(String email);

    boolean phoneExists(String phone);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long getTotalCustomers();

    long getActiveCustomers();

    long getInactiveCustomers();

    long getBlockedCustomers();

    long getBusinessCustomers();

    long getIndividualCustomers();

    // ==========================================
    // Future AI Methods
    // ==========================================

    void analyzeCustomerRisk();

    void updateCustomerAnalytics();

}