package com.example.demo.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Warehouse;

@Repository
public interface WarehouseRepository
        extends JpaRepository<Warehouse, Long> {

    // ==========================================
    // Find Warehouse By Code
    // ==========================================

    Optional<Warehouse> findByWarehouseCode(
            String warehouseCode);

    // ==========================================
    // Check Warehouse Code Exists
    // ==========================================

    boolean existsByWarehouseCode(
            String warehouseCode);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long countByStatus(String status);

    List<Warehouse> findByWarehouseCodeContainingIgnoreCaseOrWarehouseNameContainingIgnoreCaseOrCityContainingIgnoreCase(String warehouseCode, String warehouseName, String city);

}
