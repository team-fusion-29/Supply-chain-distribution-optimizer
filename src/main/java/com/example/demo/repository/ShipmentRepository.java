package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Shipment;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    // ==========================================
    // Find Shipment by Tracking Number
    // ==========================================

    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    // ==========================================
    // Check Tracking Number Exists
    // ==========================================

    boolean existsByTrackingNumber(String trackingNumber);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long countByStatus(String status);

    @Query("SELECT COALESCE(SUM(s.shippingCost),0) FROM Shipment s")
    Double getTotalRevenue();
    
    List<Shipment> findTop5ByOrderByCreatedAtDesc();

    List<Shipment> findByCustomerName(String customerName);

    List<Shipment> findByDriverName(String driverName);

    List<Shipment> findByTrackingNumberContainingIgnoreCaseOrCustomerNameContainingIgnoreCaseOrSourceContainingIgnoreCaseOrDestinationContainingIgnoreCase(String trackingNumber, String customerName, String source, String destination);

}
