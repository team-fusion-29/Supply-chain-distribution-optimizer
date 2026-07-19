package com.example.demo.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Route;

@Repository
public interface RouteRepository
        extends JpaRepository<Route, Long> {

    // ==========================================
    // Find Route By Route Code
    // ==========================================

    Optional<Route> findByRouteCode(String routeCode);

    // ==========================================
    // Check Route Code Exists
    // ==========================================

    boolean existsByRouteCode(String routeCode);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long countByStatus(String status);

    List<Route> findByRouteCodeContainingIgnoreCaseOrSourceContainingIgnoreCaseOrDestinationContainingIgnoreCase(String routeCode, String source, String destination);

}
