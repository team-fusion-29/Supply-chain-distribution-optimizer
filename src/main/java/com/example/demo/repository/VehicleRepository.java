package com.example.demo.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // ==========================================
    // Find Vehicle by Vehicle Number
    // ==========================================

    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);

    // ==========================================
    // Check Vehicle Number Exists
    // ==========================================

    boolean existsByVehicleNumber(String vehicleNumber);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long countByStatus(String status);

    List<Vehicle> findByVehicleNumberContainingIgnoreCaseOrVehicleTypeContainingIgnoreCaseOrDriverNameContainingIgnoreCase(String vehicleNumber, String vehicleType, String driverName);

}
