package com.example.demo.service;

import java.util.Map;

public interface DriverDashboardService {

    Map<String, Object> getDashboardData(String driverEmail);

    void updateShipmentStatus(String trackingNumber, String newStatus);

}
