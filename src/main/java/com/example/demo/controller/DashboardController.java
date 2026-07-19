package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.service.CustomerService;
import com.example.demo.service.DriverService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.ShipmentService;
import com.example.demo.service.VehicleService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    // ==========================================
    // Services
    // ==========================================

    private final ShipmentService shipmentService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;
    private final DriverService driverService;
    private final NotificationService notificationService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public DashboardController(ShipmentService shipmentService,
                               VehicleService vehicleService,
                               CustomerService customerService,
                               DriverService driverService,
                               NotificationService notificationService) {

        this.shipmentService = shipmentService;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.driverService = driverService;
        this.notificationService = notificationService;

    }

    // ==========================================
    // Dashboard Route Handler (Redirection)
    // ==========================================

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        if (loggedInUser.getRole() == UserRole.ADMIN) {
            return "redirect:/admin/dashboard";
        } else if (loggedInUser.getRole() == UserRole.CUSTOMER) {
            return "redirect:/customer/dashboard";
        } else if (loggedInUser.getRole() == UserRole.DRIVER) {
            return "redirect:/driver/dashboard";
        }

        return "redirect:/login";

    }

}