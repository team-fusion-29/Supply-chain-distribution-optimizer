package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.service.CustomerDashboardService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class CustomerDashboardController {

    private final CustomerDashboardService customerDashboardService;

    public CustomerDashboardController(CustomerDashboardService customerDashboardService) {
        this.customerDashboardService = customerDashboardService;
    }

    @GetMapping("/customer/dashboard")
    public String customerDashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // Authentication & Role check
        if (loggedInUser == null || loggedInUser.getRole() != UserRole.CUSTOMER) {
            return "redirect:/login";
        }

        model.addAttribute("user", loggedInUser);

        // Fetch metrics and statistics
        Map<String, Object> data = customerDashboardService.getDashboardData(loggedInUser.getEmail());
        if (Boolean.FALSE.equals(data.get("profileAvailable"))) {
            model.addAttribute("message", "Your customer profile is not available. Please contact an administrator.");
            return "auth/access-denied";
        }
        model.addAllAttributes(data);

        return "customer/dashboard/index";
    }

}
