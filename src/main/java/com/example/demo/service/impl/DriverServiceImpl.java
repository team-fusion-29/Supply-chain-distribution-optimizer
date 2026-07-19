package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Driver;
import com.example.demo.repository.DriverRepository;
import com.example.demo.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

    // ==========================================
    // Repository
    // ==========================================

    private final DriverRepository driverRepository;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public DriverServiceImpl(DriverRepository driverRepository) {

        this.driverRepository = driverRepository;

    }

    // ==========================================
    // Get All Drivers
    // ==========================================

    @Override
    public List<Driver> getAllDrivers() {

        return driverRepository.findAll();

    }

    // ==========================================
    // Get Driver By ID
    // ==========================================

    @Override
    public Driver getDriverById(Long id) {

        return driverRepository.findById(id)
                .orElse(null);

    }

    // ==========================================
    // Save Driver
    // ==========================================

    @Override
    public Driver saveDriver(Driver driver) {

        return driverRepository.save(driver);

    }

    // ==========================================
    // Update Driver
    // ==========================================

    @Override
    public Driver updateDriver(Driver driver) {
        if (driver.getId() == null || !driverRepository.existsById(driver.getId())) {
            throw new IllegalArgumentException("Driver not found.");
        }
        return driverRepository.save(driver);

    }

    // ==========================================
    // Delete Driver
    // ==========================================

    @Override
    public void deleteDriver(Long id) {

        driverRepository.deleteById(id);

    }

    // ==========================================
    // Get Driver By Driver ID
    // ==========================================

    @Override
    public Driver getDriverByDriverId(String driverId) {

        return driverRepository.findByDriverId(driverId)
                .orElse(null);

    }

    // ==========================================
    // Get Driver By Email
    // ==========================================

    @Override
    public Driver getDriverByEmail(String email) {

        return driverRepository.findByEmail(email)
                .orElse(null);

    }

    // ==========================================
    // Get Driver By Phone
    // ==========================================

    @Override
    public Driver getDriverByPhone(String phone) {

        return driverRepository.findByPhone(phone)
                .orElse(null);

    }

    // ==========================================
    // Get Driver By License Number
    // ==========================================

    @Override
    public Driver getDriverByLicenseNumber(String licenseNumber) {

        return driverRepository.findByLicenseNumber(licenseNumber)
                .orElse(null);

    }

// ==========================================
// Get Drivers By Status
// ==========================================

@Override
public List<Driver> getDriversByStatus(String status) {

    return driverRepository.findByStatus(status);

}

// ==========================================
// Get Drivers By City
// ==========================================

@Override
public List<Driver> getDriversByCity(String city) {

    return driverRepository.findByCity(city);

}

// ==========================================
// Get Drivers By State
// ==========================================

@Override
public List<Driver> getDriversByState(String state) {

    return driverRepository.findByState(state);

}

// ==========================================
// Get Drivers By Assigned Vehicle
// ==========================================

@Override
public List<Driver> getDriversByAssignedVehicle(String vehicleNumber) {

    return driverRepository.findByAssignedVehicle(vehicleNumber);

}

// ==========================================
// Get Drivers By Assigned Shipment
// ==========================================

@Override
public List<Driver> getDriversByAssignedShipment(String shipmentNumber) {

    return driverRepository.findByAssignedShipment(shipmentNumber);

}

// ==========================================
// Check Driver ID Exists
// ==========================================

@Override
public boolean driverIdExists(String driverId) {

    return driverRepository.existsByDriverId(driverId);

}

// ==========================================
// Check Email Exists
// ==========================================

@Override
public boolean emailExists(String email) {

    return driverRepository.existsByEmail(email);

}

// ==========================================
// Check Phone Exists
// ==========================================

@Override
public boolean phoneExists(String phone) {

    return driverRepository.existsByPhone(phone);

}

// ==========================================
// Check License Number Exists
// ==========================================

@Override
public boolean licenseNumberExists(String licenseNumber) {

    return driverRepository.existsByLicenseNumber(licenseNumber);

}

// ==========================================
// Check Aadhaar Number Exists
// ==========================================

@Override
public boolean aadhaarNumberExists(String aadhaarNumber) {

    return driverRepository.existsByAadhaarNumber(aadhaarNumber);

}

// ==========================================
// Get Available Drivers
// ==========================================

@Override
public List<Driver> getAvailableDrivers() {

    return driverRepository.findByStatus("AVAILABLE");

}

@Override
public List<Driver> searchDrivers(String query) {
    if (query == null || query.isBlank()) return driverRepository.findAll();
    String term = query.trim();
    return driverRepository.findByDriverIdContainingIgnoreCaseOrFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term, term);
}
// ==========================================
// Dashboard Statistics
// ==========================================

@Override
public long getTotalDrivers() {

    return driverRepository.count();

}

@Override
public long getAvailableDriversCount() {

    return driverRepository.countByStatus("AVAILABLE");

}

@Override
public long getOnTripDriversCount() {

    return driverRepository.countByStatus("ON_TRIP");

}

@Override
public long getOfflineDriversCount() {

    return driverRepository.countByStatus("OFFLINE");

}

@Override
public long getOnLeaveDriversCount() {

    return driverRepository.countByStatus("ON_LEAVE");

}

}
