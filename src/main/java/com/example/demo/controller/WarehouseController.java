package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.entity.User;
import com.example.demo.entity.Warehouse;
import com.example.demo.service.WarehouseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WarehouseController {

    // ==========================================
    // Service
    // ==========================================

    private final WarehouseService warehouseService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public WarehouseController(WarehouseService warehouseService) {

        this.warehouseService = warehouseService;

    }

    // ==========================================
    // View All Warehouses
    // ==========================================

    @GetMapping("/warehouses")
    public String warehouseList(HttpSession session, Model model,
                                @RequestParam(required = false) String search,
                                @RequestParam(required = false) String status) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        // Get Warehouses

        List<Warehouse> warehouseList = warehouseService.searchWarehouses(search);
        if (status != null && !status.isBlank()) warehouseList = warehouseList.stream()
                .filter(warehouse -> status.equalsIgnoreCase(warehouse.getStatus())).toList();

        model.addAttribute("user", loggedInUser);

        model.addAttribute("warehouses", warehouseList);

        // Dashboard Statistics

        model.addAttribute(
                "activeWarehouses",
                warehouseService.getActiveWarehouseCount());

        model.addAttribute(
                "fullWarehouses",
                warehouseService.getFullWarehouseCount());

        model.addAttribute(
                "maintenanceWarehouses",
                warehouseService.getMaintenanceWarehouseCount());

        return "warehouse/list";

    }

    // ==========================================
    // Open Add Warehouse Page
    // ==========================================

    @GetMapping("/warehouses/add")
    public String addWarehousePage(HttpSession session,
                                   Model model) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        model.addAttribute("warehouse", new Warehouse());

        model.addAttribute("user", loggedInUser);

        return "warehouse/add";

    }

    // ==========================================
    // Save Warehouse
    // ==========================================

    @PostMapping("/warehouses/save")
    public String saveWarehouse(
            @Valid @ModelAttribute("warehouse") Warehouse warehouse, BindingResult result,
            HttpSession session,
            Model model) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        if (result.hasErrors()) { model.addAttribute("user", loggedInUser); return "warehouse/add"; }
        // Duplicate Warehouse Code

        if (warehouseService.warehouseCodeExists(
                warehouse.getWarehouseCode())) {

            model.addAttribute("user", loggedInUser);

            model.addAttribute("warehouse", warehouse);

            model.addAttribute(
                    "error",
                    "Warehouse Code already exists.");

            return "warehouse/add";

        }

        // Default Status

        if (warehouse.getStatus() == null ||
                warehouse.getStatus().isBlank()) {

            warehouse.setStatus("ACTIVE");

        }

        warehouseService.saveWarehouse(warehouse);

        return "redirect:/warehouses";

    }

    // ==========================================
    // Open Edit Warehouse Page
    // ==========================================

    @GetMapping("/warehouses/view/{id}")
    public String viewWarehouse(@PathVariable Long id, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        if (warehouse == null) return "redirect:/warehouses";
        model.addAttribute("user", loggedInUser);
        model.addAttribute("warehouse", warehouse);
        return "warehouse/view";
    }

    @GetMapping("/warehouses/edit/{id}")
    public String editWarehousePage(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        Warehouse warehouse =
                warehouseService.getWarehouseById(id);

        model.addAttribute("user", loggedInUser);

        model.addAttribute("warehouse", warehouse);

        return "warehouse/edit";

    }

    // ==========================================
    // Update Warehouse
    // ==========================================

    @PostMapping("/warehouses/update")
    public String updateWarehouse(
            @Valid @ModelAttribute Warehouse warehouse, BindingResult result,
            HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        if (result.hasErrors()) return "redirect:/warehouses/edit/" + warehouse.getId();
        warehouseService.updateWarehouse(warehouse);

        return "redirect:/warehouses";

    }

    // ==========================================
    // Delete Warehouse
    // ==========================================

    @GetMapping("/warehouses/delete/{id}")
    public String deleteWarehouse(
            @PathVariable Long id,
            HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        warehouseService.deleteWarehouse(id);

        return "redirect:/warehouses";

    }

}
