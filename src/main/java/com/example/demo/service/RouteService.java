package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Route;

public interface RouteService {

    // ==========================================
    // Save Route
    // ==========================================

    Route saveRoute(Route route);

    // ==========================================
    // Get All Routes
    // ==========================================

    List<Route> getAllRoutes();

    // ==========================================
    // Get Route By Id
    // ==========================================

    Route getRouteById(Long id);

    // ==========================================
    // Update Route
    // ==========================================

    Route updateRoute(Route route);

    // ==========================================
    // Delete Route
    // ==========================================

    void deleteRoute(Long id);

    // ==========================================
    // Check Route Code Exists
    // ==========================================

    boolean routeCodeExists(String routeCode);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long getTotalRoutes();

    long getActiveRoutes();

    long getBlockedRoutes();

    long getMaintenanceRoutes();

    List<Route> searchRoutes(String query);

}
