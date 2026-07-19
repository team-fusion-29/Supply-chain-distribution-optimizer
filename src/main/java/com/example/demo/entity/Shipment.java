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
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "shipments")
public class Shipment {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // Shipment Information
    // ==========================================

    @Column(nullable = false, unique = true, length = 30)
    @NotBlank(message = "Tracking number is required.") private String trackingNumber;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Customer name is required.") private String customerName;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Source is required.") private String source;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Destination is required.") private String destination;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Vehicle number is required.") private String vehicleNumber;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Driver name is required.") private String driverName;

    @Column(nullable = false)
    @NotNull(message = "Shipment date is required.") private LocalDate shipmentDate;

    @Column(nullable = false)
    @NotNull(message = "Expected delivery date is required.") @FutureOrPresent(message = "Expected delivery date cannot be in the past.") private LocalDate expectedDeliveryDate;

    @Column(nullable = false)
    @NotNull @Positive(message = "Weight must be positive.") private Double weight;

    @Column(nullable = false)
    @NotNull @Positive(message = "Shipping cost must be positive.") private Double shippingCost;
    // ==========================================
    // Shipment Status
    // ==========================================

    @Column(nullable = false, length = 30)
    private String status;

    @Column(nullable = false, length = 20)
    private String priority;

    @Column(length = 500)
    private String description;


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

    public Shipment() {

    }

    public Shipment(Long id,
                    String trackingNumber,
                    String customerName,
                    String source,
                    String destination,
                    String vehicleNumber,
                    String driverName,
                    LocalDate shipmentDate,
                    LocalDate expectedDeliveryDate,
                    Double weight,
                    Double shippingCost,
                    String status,
                    String priority,
                    String description,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {

        this.id = id;
        this.trackingNumber = trackingNumber;
        this.customerName = customerName;
        this.source = source;
        this.destination = destination;
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
        this.shipmentDate = shipmentDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.weight = weight;
        this.shippingCost = shippingCost;
        this.status = status;
        this.priority = priority;
        this.description = description;
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

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(LocalDate shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
