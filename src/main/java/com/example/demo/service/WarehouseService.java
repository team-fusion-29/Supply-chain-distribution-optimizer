package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Warehouse;

public interface WarehouseService {

    // ==========================================
    // Save Warehouse
    // ==========================================

    Warehouse saveWarehouse(Warehouse warehouse);

    // ==========================================
    // Get All Warehouses
    // ==========================================

    List<Warehouse> getAllWarehouses();

    // ==========================================
    // Get Warehouse By Id
    // ==========================================

    Warehouse getWarehouseById(Long id);

    // ==========================================
    // Update Warehouse
    // ==========================================

    Warehouse updateWarehouse(Warehouse warehouse);

    // ==========================================
    // Delete Warehouse
    // ==========================================

    void deleteWarehouse(Long id);

    // ==========================================
    // Check Warehouse Code Exists
    // ==========================================

    boolean warehouseCodeExists(String warehouseCode);

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    long getActiveWarehouseCount();

    long getFullWarehouseCount();

    long getMaintenanceWarehouseCount();

    List<Warehouse> searchWarehouses(String query);

}
