package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // Vehicle Information
    // ==========================================

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "Vehicle number is required.") private String vehicleNumber;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Vehicle type is required.") private String vehicleType;

    @Column(nullable = false, length = 100)
    private String driverName;

    @Column(nullable = false, length = 15)
    private String driverPhone;

    @Column(nullable = false)
    @NotNull @Positive(message = "Capacity must be positive.") private Double capacity;

    @Column(nullable = false, length = 30)
    private String fuelType;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Status is required.") private String status;
    
    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 100)
    private String currentLocation;

    @Column(nullable = false)
    private LocalDate lastServiceDate;

    @Column(nullable = false)
    private LocalDate insuranceExpiry;

    // ==========================================
    // Audit Fields
    // ==========================================

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // ==========================================
    // Constructors
    // ==========================================

    public Vehicle() {
    }

    public Vehicle(Long id,
                   String vehicleNumber,
                   String vehicleType,
                   String driverName,
                   String driverPhone,
                   Double capacity,
                   String fuelType,
                   String status,
                   String description,
                   String currentLocation,
                   LocalDate lastServiceDate,
                   LocalDate insuranceExpiry,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {

        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.status = status;
        this.description = description;
        this.currentLocation = currentLocation;
        this.lastServiceDate = lastServiceDate;
        this.insuranceExpiry = insuranceExpiry;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ==========================================
    // JPA Callbacks
    // ==========================================

    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

    }

    @PreUpdate
    public void onUpdate() {

        updatedAt = LocalDateTime.now();

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

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getStatus() {
        return status;
    }
    

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(LocalDate lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public LocalDate getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public void setInsuranceExpiry(LocalDate insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
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

}
