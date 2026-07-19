package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    // ==========================================
    // Repository
    // ==========================================

    private final CustomerRepository customerRepository;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public CustomerServiceImpl(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;

    }

    // ==========================================
    // Get All Customers
    // ==========================================

    @Override
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();

    }

    // ==========================================
    // Get Customer By ID
    // ==========================================

    @Override
    public Customer getCustomerById(Long id) {

        return customerRepository.findById(id)
                .orElse(null);

    }

    // ==========================================
    // Save Customer
    // ==========================================

    @Override
    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);

    }

    // ==========================================
    // Update Customer
    // ==========================================

    @Override
    public Customer updateCustomer(Customer customer) {

        return customerRepository.save(customer);

    }

    // ==========================================
    // Delete Customer
    // ==========================================

    @Override
    public void deleteCustomer(Long id) {

        customerRepository.deleteById(id);

    }

    // ==========================================
    // Get Customer By Customer ID
    // ==========================================

    @Override
    public Customer getCustomerByCustomerId(String customerId) {

        return customerRepository.findByCustomerId(customerId)
                .orElse(null);

    }

    // ==========================================
    // Get Customer By Email
    // ==========================================

    @Override
    public Customer getCustomerByEmail(String email) {

        return customerRepository.findByEmail(email)
                .orElse(null);

    }

    // ==========================================
    // Get Customer By Phone
    // ==========================================

    @Override
    public Customer getCustomerByPhone(String phone) {

        return customerRepository.findByPhone(phone)
                .orElse(null);

    }
    // ==========================================
    // Get Customers By Company Name
    // ==========================================

    @Override
    public List<Customer> getCustomersByCompanyName(String companyName) {

        return customerRepository.findByCompanyName(companyName);

    }

    // ==========================================
    // Get Customers By City
    // ==========================================

    @Override
    public List<Customer> getCustomersByCity(String city) {

        return customerRepository.findByCity(city);

    }

    // ==========================================
    // Get Customers By State
    // ==========================================

    @Override
    public List<Customer> getCustomersByState(String state) {

        return customerRepository.findByState(state);

    }

    // ==========================================
    // Get Customers By Customer Type
    // ==========================================

    @Override
    public List<Customer> getCustomersByCustomerType(String customerType) {

        return customerRepository.findByCustomerType(customerType);

    }

    // ==========================================
    // Get Customers By Business Category
    // ==========================================

    @Override
    public List<Customer> getCustomersByBusinessCategory(String businessCategory) {

        return customerRepository.findByBusinessCategory(businessCategory);

    }

    // ==========================================
    // Get Customers By Status
    // ==========================================

    @Override
    public List<Customer> getCustomersByStatus(String status) {

        return customerRepository.findByStatus(status);

    }

    // ==========================================
    // Customer Exists
    // ==========================================

    @Override
    public boolean customerIdExists(String customerId) {

        return customerRepository.existsByCustomerId(customerId);

    }

    @Override
    public boolean emailExists(String email) {

        return customerRepository.existsByEmail(email);

    }

    @Override
    public boolean phoneExists(String phone) {

        return customerRepository.existsByPhone(phone);

    }
    // ==========================================
    // Dashboard Statistics
    // ==========================================

    @Override
    public long getTotalCustomers() {

        return customerRepository.count();

    }

    @Override
    public long getActiveCustomers() {

        return customerRepository.countByStatus("ACTIVE");

    }

    @Override
    public long getInactiveCustomers() {

        return customerRepository.countByStatus("INACTIVE");

    }

    @Override
    public long getBlockedCustomers() {

        return customerRepository.countByStatus("BLOCKED");

    }

    @Override
    public long getBusinessCustomers() {

        return customerRepository.countByCustomerType("BUSINESS");

    }

    @Override
    public long getIndividualCustomers() {

        return customerRepository.countByCustomerType("INDIVIDUAL");

    }

    // ==========================================
    // Analyze Customer Risk
    // ==========================================

    @Override
    public void analyzeCustomerRisk() {

        /*
         * Future Implementation
         *
         * Steps:
         * 1. Read all customer shipment history
         * 2. Analyze delivery success rate
         * 3. Check payment history
         * 4. Send data to Gemini AI
         * 5. AI returns:
         *      - Customer Risk Score
         *      - Customer Category
         *      - AI Recommendation
         * 6. Save results into database
         */

    }

    // ==========================================
    // Update Customer Analytics
    // ==========================================

    @Override
    public void updateCustomerAnalytics() {

        /*
         * Future Implementation
         *
         * Steps:
         * 1. Calculate total shipments
         * 2. Calculate completed shipments
         * 3. Calculate cancelled shipments
         * 4. Calculate delivery success %
         * 5. Update customer dashboard statistics
         * 6. Refresh AI Insights
         */

    }

}