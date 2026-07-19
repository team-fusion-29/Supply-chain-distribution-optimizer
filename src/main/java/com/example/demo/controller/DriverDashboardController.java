package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.service.DriverDashboardService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DriverDashboardController {

    private final DriverDashboardService driverDashboardService;

    public DriverDashboardController(DriverDashboardService driverDashboardService) {
        this.driverDashboardService = driverDashboardService;
    }

    @GetMapping("/driver/dashboard")
    public String driverDashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // Authentication & Role check
        if (loggedInUser == null || loggedInUser.getRole() != UserRole.DRIVER) {
            return "redirect:/login";
        }

        model.addAttribute("user", loggedInUser);

        // Retrieve metrics
        Map<String, Object> data = driverDashboardService.getDashboardData(loggedInUser.getEmail());
        if (Boolean.FALSE.equals(data.get("profileAvailable"))) {
            model.addAttribute("message", "Your driver profile is not available. Please contact an administrator.");
            return "auth/access-denied";
        }
        model.addAllAttributes(data);

        return "driver/dashboard/index";
    }

    @PostMapping("/driver/shipments/update-status")
    public String updateStatus(HttpSession session,
                               @RequestParam("trackingNumber") String trackingNumber,
                               @RequestParam("status") String status) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // Role authorization check
        if (loggedInUser == null || loggedInUser.getRole() != UserRole.DRIVER) {
            return "redirect:/login";
        }

        Map<String, Object> data = driverDashboardService.getDashboardData(loggedInUser.getEmail());
        if (Boolean.FALSE.equals(data.get("profileAvailable"))
                || data.get("shipments") instanceof java.util.List<?> shipments
                && shipments.stream().noneMatch(shipment -> ((com.example.demo.entity.Shipment) shipment)
                        .getTrackingNumber().equals(trackingNumber))) {
            return "redirect:/driver/dashboard?error=unauthorizedShipment";
        }
        driverDashboardService.updateShipmentStatus(trackingNumber, status);
        return "redirect:/driver/dashboard?success=statusUpdated";
    }

}
