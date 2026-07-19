package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    // ==========================================
    // Find Driver By Driver ID
    // ==========================================

    Optional<Driver> findByDriverId(String driverId);

    // ==========================================
    // Find Driver By Email
    // ==========================================

    Optional<Driver> findByEmail(String email);

    // ==========================================
    // Find Driver By Phone
    // ==========================================

    Optional<Driver> findByPhone(String phone);

    // ==========================================
    // Find Driver By License Number
    // ==========================================

    Optional<Driver> findByLicenseNumber(String licenseNumber);

    // ==========================================
    // Find Drivers By Status
    // ==========================================

    List<Driver> findByStatus(String status);

    // ==========================================
    // Find Drivers By City
    // ==========================================

    List<Driver> findByCity(String city);

    // ==========================================
    // Find Drivers By State
    // ==========================================

    List<Driver> findByState(String state);

    // ==========================================
    // Find Driver By Assigned Vehicle
    // ==========================================

    List<Driver> findByAssignedVehicle(String assignedVehicle);

    // ==========================================
    // Find Driver By Assigned Shipment
    // ==========================================

    List<Driver> findByAssignedShipment(String assignedShipment);

    // ==========================================
    // Check Existing Records
    // ==========================================

    boolean existsByDriverId(String driverId);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByLicenseNumber(String licenseNumber);

    boolean existsByAadhaarNumber(String aadhaarNumber);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long countByStatus(String status);

    List<Driver> findByDriverIdContainingIgnoreCaseOrFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String driverId, String fullName, String email);

}
