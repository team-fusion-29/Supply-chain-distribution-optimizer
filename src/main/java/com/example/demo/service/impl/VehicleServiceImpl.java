package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Vehicle;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    // ==========================================
    // Repository
    // ==========================================

    private final VehicleRepository vehicleRepository;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {

        this.vehicleRepository = vehicleRepository;

    }

    // ==========================================
    // Save Vehicle
    // ==========================================

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);

    }

    // ==========================================
    // Get All Vehicles
    // ==========================================

    @Override
    public List<Vehicle> getAllVehicles() {

        return vehicleRepository.findAll();

    }

    // ==========================================
    // Get Vehicle By Id
    // ==========================================

    @Override
    public Vehicle getVehicleById(Long id) {

        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found with ID : " + id));

    }

    // ==========================================
    // Update Vehicle
    // ==========================================

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        if (vehicle.getId() == null || !vehicleRepository.existsById(vehicle.getId())) {
            throw new IllegalArgumentException("Vehicle not found.");
        }
        return vehicleRepository.save(vehicle);

    }

    // ==========================================
    // Delete Vehicle
    // ==========================================

    @Override
    public void deleteVehicle(Long id) {

        vehicleRepository.deleteById(id);

    }

    // ==========================================
    // Check Vehicle Number Exists
    // ==========================================

    @Override
    public boolean vehicleNumberExists(String vehicleNumber) {

        return vehicleRepository.existsByVehicleNumber(vehicleNumber);

    }

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    @Override
    public long getActiveVehicleCount() {

        return vehicleRepository.countByStatus("ACTIVE");

    }

    @Override
    public long getInServiceVehicleCount() {

        return vehicleRepository.countByStatus("IN_SERVICE");

    }

    @Override
    public long getMaintenanceVehicleCount() {

        return vehicleRepository.countByStatus("UNDER_MAINTENANCE");

    }

    @Override
    public List<Vehicle> searchVehicles(String query) {
        if (query == null || query.isBlank()) return vehicleRepository.findAll();
        String term = query.trim();
        return vehicleRepository.findByVehicleNumberContainingIgnoreCaseOrVehicleTypeContainingIgnoreCaseOrDriverNameContainingIgnoreCase(term, term, term);
    }

}
