package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    // ==========================================
    // Find by City
    // ==========================================

    Optional<Weather> findByCity(String city);

    // ==========================================
    // Check City Exists
    // ==========================================

    boolean existsByCity(String city);

    // ==========================================
    // Find by Risk Level
    // ==========================================

    List<Weather> findByRiskLevel(String riskLevel);

    // ==========================================
    // Find by Route Status
    // ==========================================

    List<Weather> findByRouteStatus(String routeStatus);

    // ==========================================
    // Find by Weather Condition
    // ==========================================

    List<Weather> findByWeatherCondition(String weatherCondition);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long countByRiskLevel(String riskLevel);

    long countByRouteStatus(String routeStatus);

}