package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Shipment;
import com.example.demo.entity.User;
import com.example.demo.service.ShipmentService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmailService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class ShipmentController {

    // ==========================================
    // Service
    // ==========================================

    private final ShipmentService shipmentService;
    private final CustomerService customerService;
    private final EmailService emailService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public ShipmentController(ShipmentService shipmentService, CustomerService customerService, EmailService emailService) {

        this.shipmentService = shipmentService;
        this.customerService = customerService;
        this.emailService = emailService;

    }

    // ==========================================
    // View All Shipments
    // ==========================================

    @GetMapping("/shipments")
    public String shipmentList(HttpSession session, Model model,
                               @RequestParam(required = false) String search,
                               @RequestParam(required = false) String status) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        // Get Shipments

        List<Shipment> shipmentList = shipmentService.searchShipments(search);
        if (status != null && !status.isBlank()) shipmentList = shipmentList.stream()
                .filter(shipment -> status.equalsIgnoreCase(shipment.getStatus())).toList();

        model.addAttribute("user", loggedInUser);

        model.addAttribute("shipments", shipmentList);

        return "shipment/list";

    }
    // ==========================================
    // Open Add Shipment Page
    // ==========================================

    @GetMapping("/shipments/add")
    public String addShipmentPage(HttpSession session,
                                  Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        // Send Empty Shipment Object

        model.addAttribute("shipment", new Shipment());

        // Send Logged-in User

        model.addAttribute("user", loggedInUser);

        return "shipment/add";

    }
    // ==========================================
    // Save Shipment
    // ==========================================

    @PostMapping("/shipments/save")
    public String saveShipment(@Valid @ModelAttribute("shipment") Shipment shipment, BindingResult result,
                               HttpSession session,
                               Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        if (result.hasErrors()) { model.addAttribute("user", loggedInUser); return "shipment/add"; }
        // Check Duplicate Tracking Number

        if (shipmentService.trackingNumberExists(
                shipment.getTrackingNumber())) {

            model.addAttribute("user", loggedInUser);

            model.addAttribute("shipment", shipment);

            model.addAttribute(
                    "error",
                    "Tracking Number already exists.");

            return "shipment/add";

        }

        // Default Values

        if (shipment.getStatus() == null ||
                shipment.getStatus().isBlank()) {

            shipment.setStatus("PENDING");

        }

        if (shipment.getPriority() == null ||
                shipment.getPriority().isBlank()) {

            shipment.setPriority("MEDIUM");

        }

        // Save Shipment

        shipmentService.saveShipment(shipment);

        return "redirect:/shipments";

    }
    // ==========================================
    // Open Edit Shipment Page
    // ==========================================

    @GetMapping("/shipments/view/{id}")
    public String viewShipment(@PathVariable Long id, HttpSession session, Model model,
                               RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";
        try {
            model.addAttribute("user", loggedInUser);
            model.addAttribute("shipment", shipmentService.getShipmentById(id));
            return "shipment/view";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Shipment not found.");
            return "redirect:/shipments";
        }
    }

    @GetMapping("/shipments/edit/{id}")
    public String editShipmentPage(@PathVariable Long id,
                                   HttpSession session,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        try {
            Shipment shipment = shipmentService.getShipmentById(id);
            model.addAttribute("user", loggedInUser);
            model.addAttribute("shipment", shipment);
            return "shipment/edit";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Shipment not found with ID: " + id);
            return "redirect:/shipments";
        }

    }

    // ==========================================
    // Update Shipment
    // ==========================================

    @PostMapping("/shipments/update")
    public String updateShipment(@Valid @ModelAttribute Shipment shipment, BindingResult result,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        if (result.hasErrors()) { redirectAttributes.addFlashAttribute("error", "Correct the invalid shipment values."); return "redirect:/shipments/edit/" + shipment.getId(); }
        try {
            Shipment updated = shipmentService.updateShipment(shipment);
            customerService.getAllCustomers().stream()
                    .filter(customer -> customer.getFullName().equalsIgnoreCase(updated.getCustomerName()))
                    .findFirst().ifPresent(customer -> emailService.sendShipmentStatusEmail(customer.getEmail(), updated));
            redirectAttributes.addFlashAttribute("success", "Shipment updated successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update shipment: " + e.getMessage());
        }

        return "redirect:/shipments";

    }

    // ==========================================
    // Delete Shipment
    // ==========================================

    @GetMapping("/shipments/delete/{id}")
    public String deleteShipment(@PathVariable Long id,
                                 HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        shipmentService.deleteShipment(id);

        return "redirect:/shipments";

    }

}
