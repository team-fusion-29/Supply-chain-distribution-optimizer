package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Vehicle;

public interface VehicleService {

    // ==========================================
    // Save Vehicle
    // ==========================================

    Vehicle saveVehicle(Vehicle vehicle);

    // ==========================================
    // Get All Vehicles
    // ==========================================

    List<Vehicle> getAllVehicles();

    // ==========================================
    // Get Vehicle By Id
    // ==========================================

    Vehicle getVehicleById(Long id);

    // ==========================================
    // Update Vehicle
    // ==========================================

    Vehicle updateVehicle(Vehicle vehicle);

    // ==========================================
    // Delete Vehicle
    // ==========================================

    void deleteVehicle(Long id);

    // ==========================================
    // Check Vehicle Number Exists
    // ==========================================

    boolean vehicleNumberExists(String vehicleNumber);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long getActiveVehicleCount();

    long getInServiceVehicleCount();

    long getMaintenanceVehicleCount();

    List<Vehicle> searchVehicles(String query);

}
