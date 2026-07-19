package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Route;
import com.example.demo.repository.RouteRepository;
import com.example.demo.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService {

    // ==========================================
    // Repository
    // ==========================================

    private final RouteRepository routeRepository;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public RouteServiceImpl(RouteRepository routeRepository) {

        this.routeRepository = routeRepository;

    }

    // ==========================================
    // Save Route
    // ==========================================

    @Override
    public Route saveRoute(Route route) {

        return routeRepository.save(route);

    }

    // ==========================================
    // Get All Routes
    // ==========================================

    @Override
    public List<Route> getAllRoutes() {

        return routeRepository.findAll();

    }

    // ==========================================
    // Get Route By Id
    // ==========================================

    @Override
    public Route getRouteById(Long id) {

        return routeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Route not found with ID : " + id));

    }

    // ==========================================
    // Update Route
    // ==========================================

    @Override
    public Route updateRoute(Route route) {
        if (route.getId() == null || !routeRepository.existsById(route.getId())) {
            throw new IllegalArgumentException("Route not found.");
        }
        return routeRepository.save(route);

    }

    // ==========================================
    // Delete Route
    // ==========================================

    @Override
    public void deleteRoute(Long id) {

        routeRepository.deleteById(id);

    }

    // ==========================================
    // Check Route Code Exists
    // ==========================================

    @Override
    public boolean routeCodeExists(String routeCode) {

        return routeRepository.existsByRouteCode(routeCode);

    }

    // ==========================================
    // Total Routes
    // ==========================================

    @Override
    public long getTotalRoutes() {

        return routeRepository.count();

    }

    // ==========================================
    // Active Routes
    // ==========================================

    @Override
    public long getActiveRoutes() {

        return routeRepository.countByStatus("ACTIVE");

    }

    // ==========================================
    // Blocked Routes
    // ==========================================

    @Override
    public long getBlockedRoutes() {

        return routeRepository.countByStatus("BLOCKED");

    }

    // ==========================================
    // Maintenance Routes
    // ==========================================

    @Override
    public long getMaintenanceRoutes() {

        return routeRepository.countByStatus("MAINTENANCE");

    }

    @Override
    public List<Route> searchRoutes(String query) {
        if (query == null || query.isBlank()) return routeRepository.findAll();
        String term = query.trim();
        return routeRepository.findByRouteCodeContainingIgnoreCaseOrSourceContainingIgnoreCaseOrDestinationContainingIgnoreCase(term, term, term);
    }

}
