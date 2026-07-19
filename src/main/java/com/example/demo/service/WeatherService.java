package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Weather;

public interface WeatherService {

    // ==========================================
    // CRUD Operations
    // ==========================================

    List<Weather> getAllWeather();

    Weather getWeatherById(Long id);

    Weather saveWeather(Weather weather);

    Weather updateWeather(Weather weather);

    void deleteWeather(Long id);

    // ==========================================
    // City Operations
    // ==========================================

    Weather getWeatherByCity(String city);

    boolean cityExists(String city);

    // ==========================================
    // Weather Filters
    // ==========================================

    List<Weather> getWeatherByRiskLevel(String riskLevel);

    List<Weather> getWeatherByRouteStatus(String routeStatus);

    List<Weather> getWeatherByCondition(String weatherCondition);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long getTotalCities();

    long getHighRiskCount();

    long getMediumRiskCount();

    long getLowRiskCount();

    long getSafeRouteCount();

    long getBlockedRouteCount();

    // ==========================================
    // Future AI Integration
    // ==========================================

    void refreshWeatherData();

    void analyzeWeatherRisk();

}