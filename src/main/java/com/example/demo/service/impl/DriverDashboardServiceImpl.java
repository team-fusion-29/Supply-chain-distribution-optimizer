package com.example.demo.service.impl;

import com.example.demo.entity.Driver;
import com.example.demo.entity.Shipment;
import com.example.demo.service.DriverDashboardService;
import com.example.demo.service.DriverService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.ShipmentService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverDashboardServiceImpl implements DriverDashboardService {

    private final DriverService driverService;
    private final ShipmentService shipmentService;
    private final NotificationService notificationService;

    public DriverDashboardServiceImpl(DriverService driverService,
                                     ShipmentService shipmentService,
                                     NotificationService notificationService) {
        this.driverService = driverService;
        this.shipmentService = shipmentService;
        this.notificationService = notificationService;
    }

    @Override
    public Map<String, Object> getDashboardData(String driverEmail) {
        Map<String, Object> data = new HashMap<>();

        // 1. Fetch Driver details
        Driver driver = driverService.getDriverByEmail(driverEmail);

        data.put("driver", driver);

        if (driver == null) {
            data.put("profileAvailable", false);
            data.put("totalAssigned", 0L);
            data.put("todayDeliveries", 0L);
            data.put("completedDeliveries", 0L);
            data.put("pendingDeliveries", 0L);
            data.put("shipments", List.of());
            data.put("latestNotifications", List.of());
            return data;
        }
        data.put("profileAvailable", true);

        // 2. Fetch Driver Shipments by Name
        List<Shipment> shipments = shipmentService.getShipmentsByDriverName(driver.getFullName());

        long totalAssigned = shipments.size();
        
        LocalDate today = LocalDate.now();
        long todayDeliveries = shipments.stream()
                .filter(s -> today.equals(s.getExpectedDeliveryDate()) && !"DELIVERED".equalsIgnoreCase(s.getStatus()))
                .count();

        long completedDeliveries = shipments.stream()
                .filter(s -> "DELIVERED".equalsIgnoreCase(s.getStatus()))
                .count();

        long pendingDeliveries = totalAssigned - completedDeliveries;

        // Statistics
        data.put("totalAssigned", totalAssigned);
        data.put("todayDeliveries", todayDeliveries);
        data.put("completedDeliveries", completedDeliveries);
        data.put("pendingDeliveries", pendingDeliveries);

        // Lists
        data.put("shipments", shipments);
        data.put("latestNotifications", notificationService.getNotificationsByRecipient(driver.getId()));

        return data;
    }

    @Override
    public void updateShipmentStatus(String trackingNumber, String newStatus) {
        Shipment shipment = shipmentService.getShipmentByTrackingNumber(trackingNumber);
        if (shipment != null) {
            shipment.setStatus(newStatus.toUpperCase());
            shipmentService.updateShipment(shipment);
        }
    }

}
