package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // ==========================================
    // Find By Customer ID
    // ==========================================

    Optional<Customer> findByCustomerId(String customerId);

    // ==========================================
    // Find By Email
    // ==========================================

    Optional<Customer> findByEmail(String email);

    // ==========================================
    // Find By Phone
    // ==========================================

    Optional<Customer> findByPhone(String phone);

    // ==========================================
    // Find By Company Name
    // ==========================================

    List<Customer> findByCompanyName(String companyName);

    // ==========================================
    // Find By City
    // ==========================================

    List<Customer> findByCity(String city);

    // ==========================================
    // Find By State
    // ==========================================

    List<Customer> findByState(String state);

    // ==========================================
    // Find By Customer Type
    // ==========================================

    List<Customer> findByCustomerType(String customerType);

    // ==========================================
    // Find By Business Category
    // ==========================================

    List<Customer> findByBusinessCategory(String businessCategory);

    // ==========================================
    // Find By Status
    // ==========================================

    List<Customer> findByStatus(String status);

    // ==========================================
    // Exists Methods
    // ==========================================

    boolean existsByCustomerId(String customerId);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long countByStatus(String status);

    long countByCustomerType(String customerType);

    long countByBusinessCategory(String businessCategory);

}