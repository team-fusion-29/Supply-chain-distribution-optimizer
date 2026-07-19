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
@Table(name = "weather")
public class Weather {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // Location Information
    // ==========================================

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 100)
    private String country;

    // ==========================================
    // Weather Information
    // ==========================================

    @Column(nullable = false)
    private Double temperature;

    @Column(nullable = false)
    private Double feelsLike;

    @Column(nullable = false)
    private Integer humidity;

    @Column(nullable = false)
    private Double windSpeed;

    @Column(nullable = false)
    private Double visibility;

    @Column(nullable = false, length = 100)
    private String weatherCondition;

    @Column(nullable = false, length = 50)
    private String weatherIcon;

    // ==========================================
    // AI Analysis
    // ==========================================

    @Column(nullable = false, length = 30)
    private String riskLevel;

    @Column(nullable = false, length = 30)
    private String routeStatus;

    @Column(length = 500)
    private String aiSuggestion;

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

    public Weather() {
    }

    public Weather(Long id,
                   String city,
                   String state,
                   String country,
                   Double temperature,
                   Double feelsLike,
                   Integer humidity,
                   Double windSpeed,
                   Double visibility,
                   String weatherCondition,
                   String weatherIcon,
                   String riskLevel,
                   String routeStatus,
                   String aiSuggestion,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {

        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.weatherCondition = weatherCondition;
        this.weatherIcon = weatherIcon;
        this.riskLevel = riskLevel;
        this.routeStatus = routeStatus;
        this.aiSuggestion = aiSuggestion;
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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(String routeStatus) {
        this.routeStatus = routeStatus;
    }

    public String getAiSuggestion() {
        return aiSuggestion;
    }

    public void setAiSuggestion(String aiSuggestion) {
        this.aiSuggestion = aiSuggestion;
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