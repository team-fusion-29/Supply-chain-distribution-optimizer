package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Shipment;
import com.example.demo.service.CustomerDashboardService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.ShipmentService;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerDashboardServiceImpl implements CustomerDashboardService {

    private final CustomerService customerService;
    private final ShipmentService shipmentService;
    private final NotificationService notificationService;

    public CustomerDashboardServiceImpl(CustomerService customerService,
                                       ShipmentService shipmentService,
                                       NotificationService notificationService) {
        this.customerService = customerService;
        this.shipmentService = shipmentService;
        this.notificationService = notificationService;
    }

    @Override
    public Map<String, Object> getDashboardData(String customerEmail) {
        Map<String, Object> data = new HashMap<>();

        // 1. Fetch Customer details
        Customer customer = customerService.getCustomerByEmail(customerEmail);
        
        data.put("customer", customer);

        if (customer == null) {
            data.put("profileAvailable", false);
            data.put("totalShipments", 0L);
            data.put("deliveredShipments", 0L);
            data.put("pendingShipments", 0L);
            data.put("inTransitShipments", 0L);
            data.put("cancelledShipments", 0L);
            data.put("shipments", List.of());
            data.put("recentShipments", List.of());
            data.put("latestNotifications", List.of());
            return data;
        }
        data.put("profileAvailable", true);

        // 2. Fetch Customer Shipments by Name
        List<Shipment> shipments = shipmentService.getShipmentsByCustomerName(customer.getFullName());

        long total = shipments.size();
        long delivered = shipments.stream().filter(s -> "DELIVERED".equalsIgnoreCase(s.getStatus())).count();
        long pending = shipments.stream().filter(s -> "PENDING".equalsIgnoreCase(s.getStatus())).count();
        long transit = shipments.stream().filter(s -> "IN_TRANSIT".equalsIgnoreCase(s.getStatus())).count();
        long cancelled = total - delivered - pending - transit;

        // Statistics
        data.put("totalShipments", total);
        data.put("deliveredShipments", delivered);
        data.put("pendingShipments", pending);
        data.put("inTransitShipments", transit);
        data.put("cancelledShipments", cancelled > 0 ? cancelled : 0);

        // Lists
        data.put("shipments", shipments);
        data.put("recentShipments", shipments.stream().limit(5).toList());
        data.put("latestNotifications", notificationService.getNotificationsByRecipient(customer.getId()));

        return data;
    }

}
