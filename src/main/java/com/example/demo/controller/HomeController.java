package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Contact;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ShipmentService;

@Controller
public class HomeController {

    private final ShipmentService shipmentService;
    private final CustomerService customerService;

    public HomeController(ShipmentService shipmentService, CustomerService customerService) {
        this.shipmentService = shipmentService;
        this.customerService = customerService;
    }

    // ==========================================
    // Home Page
    // ==========================================

    @GetMapping("/")
    public String home(Model model) {
        long totalShipments = shipmentService.getTotalShipments();
        long totalCustomers = customerService.getTotalCustomers();

        model.addAttribute("totalShipments", totalShipments);
        model.addAttribute("totalCustomers", totalCustomers);
        model.addAttribute("contact", new Contact());

        return "index";
    }

    // ==========================================
    // Track Shipment Page
    // ==========================================

    @GetMapping("/track")
    public String trackShipment(@RequestParam(value = "trackingNumber", required = false) String trackingNumber, Model model) {
        if (trackingNumber != null && !trackingNumber.trim().isEmpty()) {
            try {
                var shipment = shipmentService.getShipmentByTrackingNumber(trackingNumber.trim());
                model.addAttribute("shipment", shipment);
                model.addAttribute("searchedNumber", trackingNumber);
            } catch (Exception e) {
                model.addAttribute("error", "Shipment not found with tracking number: " + trackingNumber);
                model.addAttribute("searchedNumber", trackingNumber);
            }
        }
        return "customer/track-shipment";
    }

}
