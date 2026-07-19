package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    // ==========================================
    // Repository
    // ==========================================

    private final WarehouseRepository warehouseRepository;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {

        this.warehouseRepository = warehouseRepository;

    }

    // ==========================================
    // Save Warehouse
    // ==========================================

    @Override
    public Warehouse saveWarehouse(Warehouse warehouse) {
        if (warehouse.getAvailableCapacity() > warehouse.getTotalCapacity()) {
            throw new IllegalArgumentException("Available capacity cannot exceed total capacity.");
        }
        return warehouseRepository.save(warehouse);

    }

    // ==========================================
    // Get All Warehouses
    // ==========================================

    @Override
    public List<Warehouse> getAllWarehouses() {

        return warehouseRepository.findAll();

    }

    // ==========================================
    // Get Warehouse By Id
    // ==========================================

    @Override
    public Warehouse getWarehouseById(Long id) {

        return warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Warehouse not found with ID : " + id));

    }

    // ==========================================
    // Update Warehouse
    // ==========================================

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) {
        if (warehouse.getId() == null || !warehouseRepository.existsById(warehouse.getId())) {
            throw new IllegalArgumentException("Warehouse not found.");
        }
        if (warehouse.getAvailableCapacity() > warehouse.getTotalCapacity()) {
            throw new IllegalArgumentException("Available capacity cannot exceed total capacity.");
        }
        return warehouseRepository.save(warehouse);

    }

    // ==========================================
    // Delete Warehouse
    // ==========================================

    @Override
    public void deleteWarehouse(Long id) {

        warehouseRepository.deleteById(id);

    }

    // ==========================================
    // Check Warehouse Code Exists
    // ==========================================

    @Override
    public boolean warehouseCodeExists(String warehouseCode) {

        return warehouseRepository
                .existsByWarehouseCode(warehouseCode);

    }

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    @Override
    public long getActiveWarehouseCount() {

        return warehouseRepository.countByStatus("ACTIVE");

    }

    @Override
    public long getFullWarehouseCount() {

        return warehouseRepository.countByStatus("FULL");

    }

    @Override
    public long getMaintenanceWarehouseCount() {

        return warehouseRepository.countByStatus("MAINTENANCE");

    }

    @Override
    public List<Warehouse> searchWarehouses(String query) {
        if (query == null || query.isBlank()) return warehouseRepository.findAll();
        String term = query.trim();
        return warehouseRepository.findByWarehouseCodeContainingIgnoreCaseOrWarehouseNameContainingIgnoreCaseOrCityContainingIgnoreCase(term, term, term);
    }

}
