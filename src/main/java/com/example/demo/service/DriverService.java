package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Driver;

public interface DriverService {

    // ==========================================
    // CRUD Operations
    // ==========================================

    List<Driver> getAllDrivers();

    Driver getDriverById(Long id);

    Driver saveDriver(Driver driver);

    Driver updateDriver(Driver driver);

    void deleteDriver(Long id);

    // ==========================================
    // Search Operations
    // ==========================================

    Driver getDriverByDriverId(String driverId);

    Driver getDriverByEmail(String email);

    Driver getDriverByPhone(String phone);

    Driver getDriverByLicenseNumber(String licenseNumber);

    // ==========================================
    // Filter Operations
    // ==========================================

    List<Driver> getDriversByStatus(String status);

    List<Driver> getDriversByCity(String city);

    List<Driver> getDriversByState(String state);

    List<Driver> getDriversByAssignedVehicle(String vehicleNumber);

    List<Driver> getDriversByAssignedShipment(String shipmentNumber);

    // ==========================================
    // Validation
    // ==========================================

    boolean driverIdExists(String driverId);

    boolean emailExists(String email);

    boolean phoneExists(String phone);

    boolean licenseNumberExists(String licenseNumber);

    boolean aadhaarNumberExists(String aadhaarNumber);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long getTotalDrivers();

    long getAvailableDriversCount();

    long getOnTripDriversCount();

    long getOfflineDriversCount();

    long getOnLeaveDriversCount();

    // ==========================================
    // Driver Availability
    // ==========================================

    List<Driver> getAvailableDrivers();

    List<Driver> searchDrivers(String query);

}
