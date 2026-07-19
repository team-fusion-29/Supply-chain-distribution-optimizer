package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // Customer Information
    // ==========================================

    @Column(unique = true, nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    private String companyName;

    private String gstNumber;

    // ==========================================
    // Address Information
    // ==========================================

    private String address;

    private String city;

    private String state;

    private String country;

    private String pincode;
    // ==========================================
    // Customer Type
    // ==========================================

    @Column(nullable = false)
    private String customerType;

    /*
        INDIVIDUAL
        BUSINESS
    */

    // ==========================================
    // Business Category
    // ==========================================

    private String businessCategory;

    /*
        E-Commerce
        Manufacturing
        Retail
        Healthcare
        Electronics
        Food
        Others
    */

    // ==========================================
    // Shipment Statistics
    // ==========================================

    private Integer totalShipments = 0;

    private Integer activeShipments = 0;

    private Integer completedShipments = 0;

    private Integer cancelledShipments = 0;

    // ==========================================
    // Account Status
    // ==========================================

    @Column(nullable = false)
    private String status;

    /*
        ACTIVE
        INACTIVE
        BLOCKED
    */

    // ==========================================
    // Audit Information
    // ==========================================

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    // ==========================================
    // Constructors
    // ==========================================

    public Customer() {

    }

    public Customer(Long id, String customerId, String fullName,
                    String email, String phone, String password,
                    String companyName, String gstNumber,
                    String address, String city,
                    String state, String country,
                    String pincode, String customerType,
                    String businessCategory,
                    Integer totalShipments,
                    Integer activeShipments,
                    Integer completedShipments,
                    Integer cancelledShipments,
                    String status,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {

        this.id = id;
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.companyName = companyName;
        this.gstNumber = gstNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.customerType = customerType;
        this.businessCategory = businessCategory;
        this.totalShipments = totalShipments;
        this.activeShipments = activeShipments;
        this.completedShipments = completedShipments;
        this.cancelledShipments = cancelledShipments;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    // ==========================================
    // Automatically Set Audit Dates
    // ==========================================

    @PrePersist
    public void prePersist() {

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }

    @PreUpdate
    public void preUpdate() {

        this.updatedAt = LocalDateTime.now();

    }

    // ==========================================
    // Getters and Setters
    // ==========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public Integer getTotalShipments() {
        return totalShipments;
    }

    public void setTotalShipments(Integer totalShipments) {
        this.totalShipments = totalShipments;
    }

    public Integer getActiveShipments() {
        return activeShipments;
    }

    public void setActiveShipments(Integer activeShipments) {
        this.activeShipments = activeShipments;
    }

    public Integer getCompletedShipments() {
        return completedShipments;
    }

    public void setCompletedShipments(Integer completedShipments) {
        this.completedShipments = completedShipments;
    }

    public Integer getCancelledShipments() {
        return cancelledShipments;
    }

    public void setCancelledShipments(Integer cancelledShipments) {
        this.cancelledShipments = cancelledShipments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ==========================================
    // toString()
    // ==========================================

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", customerType='" + customerType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}