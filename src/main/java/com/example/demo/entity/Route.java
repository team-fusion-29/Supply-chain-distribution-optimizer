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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "routes")
public class Route {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // Route Information
    // ==========================================

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "Route code is required.") private String routeCode;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Source is required.") private String source;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Destination is required.") private String destination;

    @Column(nullable = false)
    @NotNull @Positive(message = "Distance must be positive.") private Double distance;

    @Column(nullable = false)
    @NotNull @Positive(message = "Estimated time must be positive.") private Double estimatedTime;

    @Column(nullable = false, length = 30)
    private String roadType;

    @Column(nullable = false, length = 30)
    private String trafficLevel;

    @Column(nullable = false, length = 30)
    private String weatherCondition;

    @Column(nullable = false, length = 30)
    private String status;

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

    public Route() {

    }

    public Route(Long id,
                 String routeCode,
                 String source,
                 String destination,
                 Double distance,
                 Double estimatedTime,
                 String roadType,
                 String trafficLevel,
                 String weatherCondition,
                 String status,
                 String description,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt) {

        this.id = id;
        this.routeCode = routeCode;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.roadType = roadType;
        this.trafficLevel = trafficLevel;
        this.weatherCondition = weatherCondition;
        this.status = status;
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

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public String getTrafficLevel() {
        return trafficLevel;
    }

    public void setTrafficLevel(String trafficLevel) {
        this.trafficLevel = trafficLevel;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
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
