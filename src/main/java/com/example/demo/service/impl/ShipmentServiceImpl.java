package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Shipment;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    // ==========================================
    // Repository
    // ==========================================

    private final ShipmentRepository shipmentRepository;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {

        this.shipmentRepository = shipmentRepository;

    }

    // ==========================================
    // Save Shipment
    // ==========================================

    @Override
    public Shipment saveShipment(Shipment shipment) {

        return shipmentRepository.save(shipment);

    }

    // ==========================================
    // Get All Shipments
    // ==========================================

    @Override
    public List<Shipment> getAllShipments() {

        return shipmentRepository.findAll();

    }
    // ==========================================
    // Get Shipment By Id
    // ==========================================

    @Override
    public Shipment getShipmentById(Long id) {

        return shipmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shipment not found with ID : " + id));

    }

    @Override
    public Shipment updateShipment(Shipment shipment) {

        Shipment existing = shipmentRepository.findById(shipment.getId())
                .orElseThrow(() -> new RuntimeException("Shipment not found with ID : " + shipment.getId()));

        existing.setTrackingNumber(shipment.getTrackingNumber());
        existing.setCustomerName(shipment.getCustomerName());
        existing.setSource(shipment.getSource());
        existing.setDestination(shipment.getDestination());
        existing.setVehicleNumber(shipment.getVehicleNumber());
        existing.setDriverName(shipment.getDriverName());
        existing.setShipmentDate(shipment.getShipmentDate());
        existing.setExpectedDeliveryDate(shipment.getExpectedDeliveryDate());
        existing.setWeight(shipment.getWeight());
        existing.setShippingCost(shipment.getShippingCost());
        existing.setStatus(shipment.getStatus());
        existing.setPriority(shipment.getPriority());
        existing.setDescription(shipment.getDescription());

        return shipmentRepository.save(existing);

    }

    // ==========================================
    // Delete Shipment
    // ==========================================

    @Override
    public void deleteShipment(Long id) {

        shipmentRepository.deleteById(id);

    }
    // ==========================================
    // Check Tracking Number Exists
    // ==========================================

    @Override
    public boolean trackingNumberExists(String trackingNumber) {

        return shipmentRepository.existsByTrackingNumber(trackingNumber);
        

    }
 // ==========================================
 // Dashboard Statistics
 // ==========================================

 @Override
 public long getTotalShipments() {

     return shipmentRepository.count();

 }

 // ==========================================
 // Pending Shipments
 // ==========================================

 @Override
 public long getPendingShipments() {

     return shipmentRepository.countByStatus("PENDING");

 }

 // ==========================================
 // In Transit Shipments
 // ==========================================

 @Override
 public long getInTransitShipments() {

     return shipmentRepository.countByStatus("IN_TRANSIT");

 }

 // ==========================================
 // Delivered Shipments
 // ==========================================

 @Override
 public long getDeliveredShipments() {

     return shipmentRepository.countByStatus("DELIVERED");

 }

 // ==========================================
 // Total Revenue
 // ==========================================

 @Override
 public Double getTotalRevenue() {

     Double revenue = shipmentRepository.getTotalRevenue();

     return revenue == null ? 0.0 : revenue;

 }
 
 @Override
 public List<Shipment> getRecentShipments() {

     return shipmentRepository.findTop5ByOrderByCreatedAtDesc();

 }
    
  @Override
  public Shipment getShipmentByTrackingNumber(String trackingNumber) {
      return shipmentRepository.findByTrackingNumber(trackingNumber)
              .orElseThrow(() -> new RuntimeException("Shipment not found with tracking number: " + trackingNumber));
  }

  @Override
  public List<Shipment> getShipmentsByCustomerName(String customerName) {
      return shipmentRepository.findByCustomerName(customerName);
  }

  @Override
  public List<Shipment> getShipmentsByDriverName(String driverName) {
      return shipmentRepository.findByDriverName(driverName);
  }

  @Override
  public List<Shipment> searchShipments(String query) {
      if (query == null || query.isBlank()) return shipmentRepository.findAll();
      String term = query.trim();
      return shipmentRepository.findByTrackingNumberContainingIgnoreCaseOrCustomerNameContainingIgnoreCaseOrSourceContainingIgnoreCaseOrDestinationContainingIgnoreCase(term, term, term, term);
  }

}
