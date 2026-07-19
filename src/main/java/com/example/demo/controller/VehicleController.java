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
import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;

import jakarta.servlet.http.HttpSession;

@Controller
public class VehicleController {

    // ==========================================
    // Service
    // ==========================================

    private final VehicleService vehicleService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public VehicleController(VehicleService vehicleService) {

        this.vehicleService = vehicleService;

    }

    // ==========================================
    // View All Vehicles
    // ==========================================

    @GetMapping("/vehicles")
    public String vehicleList(HttpSession session, Model model,
                              @RequestParam(required = false) String search,
                              @RequestParam(required = false) String status) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        // Get Vehicles

     // Get Vehicles

        List<Vehicle> vehicleList = vehicleService.searchVehicles(search);
        if (status != null && !status.isBlank()) vehicleList = vehicleList.stream()
                .filter(vehicle -> status.equalsIgnoreCase(vehicle.getStatus())).toList();

        model.addAttribute("user", loggedInUser);

        model.addAttribute("vehicles", vehicleList);

        // Vehicle Statistics

        model.addAttribute(
                "activeVehicles",
                vehicleService.getActiveVehicleCount());

        model.addAttribute(
                "inServiceVehicles",
                vehicleService.getInServiceVehicleCount());

        model.addAttribute(
                "maintenanceVehicles",
                vehicleService.getMaintenanceVehicleCount());

        return "vehicle/list";

    }

    // ==========================================
    // Open Add Vehicle Page
    // ==========================================

    @GetMapping("/vehicles/add")
    public String addVehiclePage(HttpSession session,
                                 Model model) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        model.addAttribute("vehicle", new Vehicle());

        model.addAttribute("user", loggedInUser);

        return "vehicle/add";

    }
    // ==========================================
    // Save Vehicle
    // ==========================================

    @PostMapping("/vehicles/save")
    public String saveVehicle(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult result,
                              HttpSession session,
                              Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        if (result.hasErrors()) { model.addAttribute("user", loggedInUser); return "vehicle/add"; }
        // Check Duplicate Vehicle Number

        if (vehicleService.vehicleNumberExists(
                vehicle.getVehicleNumber())) {

            model.addAttribute("user", loggedInUser);

            model.addAttribute("vehicle", vehicle);

            model.addAttribute(
                    "error",
                    "Vehicle Number already exists.");

            return "vehicle/add";

        }

        // Default Status

        if (vehicle.getStatus() == null ||
                vehicle.getStatus().isBlank()) {

            vehicle.setStatus("ACTIVE");

        }

        // Save Vehicle

        vehicleService.saveVehicle(vehicle);

        return "redirect:/vehicles";
        
        

    }
    // ==========================================
    // Open Edit Vehicle Page
    // ==========================================

    @GetMapping("/vehicles/view/{id}")
    public String viewVehicle(@PathVariable Long id, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null) return "redirect:/vehicles";
        model.addAttribute("user", loggedInUser);
        model.addAttribute("vehicle", vehicle);
        return "vehicle/view";
    }

    @GetMapping("/vehicles/edit/{id}")
    public String editVehiclePage(@PathVariable Long id,
                                  HttpSession session,
                                  Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        Vehicle vehicle = vehicleService.getVehicleById(id);

        model.addAttribute("user", loggedInUser);

        model.addAttribute("vehicle", vehicle);

        return "vehicle/edit";

    }

    // ==========================================
    // Update Vehicle
    // ==========================================

    @PostMapping("/vehicles/update")
    public String updateVehicle(@Valid @ModelAttribute Vehicle vehicle, BindingResult result,
                                HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        if (result.hasErrors()) return "redirect:/vehicles/edit/" + vehicle.getId();
        vehicleService.updateVehicle(vehicle);

        return "redirect:/vehicles";

    }
    

    // ==========================================
    // Delete Vehicle
    // ==========================================

    @GetMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable Long id,
                                HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        vehicleService.deleteVehicle(id);

        return "redirect:/vehicles";

    }
    

}
    
