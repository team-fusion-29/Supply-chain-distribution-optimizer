package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Weather;
import com.example.demo.repository.WeatherRepository;
import com.example.demo.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

    // ==========================================
    // Repository
    // ==========================================

    private final WeatherRepository weatherRepository;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public WeatherServiceImpl(WeatherRepository weatherRepository) {

        this.weatherRepository = weatherRepository;

    }

    // ==========================================
    // Get All Weather
    // ==========================================

    @Override
    public List<Weather> getAllWeather() {

        return weatherRepository.findAll();

    }

    // ==========================================
    // Get Weather By ID
    // ==========================================

    @Override
    public Weather getWeatherById(Long id) {

        return weatherRepository.findById(id)
                .orElse(null);

    }

    // ==========================================
    // Save Weather
    // ==========================================

    @Override
    public Weather saveWeather(Weather weather) {

        return weatherRepository.save(weather);

    }

    // ==========================================
    // Update Weather
    // ==========================================

    @Override
    public Weather updateWeather(Weather weather) {

        return weatherRepository.save(weather);

    }

    // ==========================================
    // Delete Weather
    // ==========================================

    @Override
    public void deleteWeather(Long id) {

        weatherRepository.deleteById(id);

    }

    // ==========================================
    // Get Weather By City
    // ==========================================

    @Override
    public Weather getWeatherByCity(String city) {

        return weatherRepository.findByCity(city)
                .orElse(null);

    }

    // ==========================================
    // Check City Exists
    // ==========================================

    @Override
    public boolean cityExists(String city) {

        return weatherRepository.existsByCity(city);

    }

// ==========================================
// Get Weather By Risk Level
// ==========================================

@Override
public List<Weather> getWeatherByRiskLevel(String riskLevel) {

    return weatherRepository.findByRiskLevel(riskLevel);

}

// ==========================================
// Get Weather By Route Status
// ==========================================

@Override
public List<Weather> getWeatherByRouteStatus(String routeStatus) {

    return weatherRepository.findByRouteStatus(routeStatus);

}

// ==========================================
// Get Weather By Condition
// ==========================================

@Override
public List<Weather> getWeatherByCondition(String weatherCondition) {

    return weatherRepository.findByWeatherCondition(weatherCondition);

}

// ==========================================
// Dashboard Statistics
// ==========================================

@Override
public long getTotalCities() {

    return weatherRepository.count();

}

@Override
public long getHighRiskCount() {

    return weatherRepository.countByRiskLevel("HIGH");

}

@Override
public long getMediumRiskCount() {

    return weatherRepository.countByRiskLevel("MEDIUM");

}

@Override
public long getLowRiskCount() {

    return weatherRepository.countByRiskLevel("LOW");

}

@Override
public long getSafeRouteCount() {

    return weatherRepository.countByRouteStatus("SAFE");

}

@Override
public long getBlockedRouteCount() {

    return weatherRepository.countByRouteStatus("BLOCKED");

}
// ==========================================
// Refresh Weather Data
// ==========================================

@Override
public void refreshWeatherData() {

    /*
     * Future Implementation
     *
     * Steps:
     * 1. Read all cities from database
     * 2. Call Weather API
     * 3. Update latest weather details
     * 4. Save updated records
     */

}

// ==========================================
// Analyze Weather Risk
// ==========================================

@Override
public void analyzeWeatherRisk() {

    /*
     * Future Implementation
     *
     * Steps:
     * 1. Read latest weather data
     * 2. Send data to Gemini AI
     * 3. AI returns:
     *      - Risk Level
     *      - Route Status
     *      - AI Suggestion
     * 4. Save results into database
     */

}

}