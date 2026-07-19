package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Shipment;

public interface ShipmentService {

    // ==========================================
    // Save Shipment
    // ==========================================

    Shipment saveShipment(Shipment shipment);

    // ==========================================
    // Get All Shipments
    // ==========================================

    List<Shipment> getAllShipments();

    // ==========================================
    // Get Shipment By Id
    // ==========================================

    Shipment getShipmentById(Long id);

    // ==========================================
    // Update Shipment
    // ==========================================

    Shipment updateShipment(Shipment shipment);

    // ==========================================
    // Delete Shipment
    // ==========================================

    void deleteShipment(Long id);

    // ==========================================
    // Check Tracking Number Exists
    // ==========================================

    boolean trackingNumberExists(String trackingNumber);
    
 // ==========================================
 // Dashboard Statistics
 // ==========================================

 long getTotalShipments();

 // ==========================================
 // Pending Shipments
 // ==========================================

 long getPendingShipments();

 // ==========================================
 // In Transit Shipments
 // ==========================================

 long getInTransitShipments();

 // ==========================================
 // Delivered Shipments
 // ==========================================

 long getDeliveredShipments();

 // ==========================================
 // Total Revenue
 // ==========================================

 Double getTotalRevenue();
 
 List<Shipment> getRecentShipments();

 Shipment getShipmentByTrackingNumber(String trackingNumber);

 List<Shipment> getShipmentsByCustomerName(String customerName);

    List<Shipment> getShipmentsByDriverName(String driverName);

    List<Shipment> searchShipments(String query);

}
