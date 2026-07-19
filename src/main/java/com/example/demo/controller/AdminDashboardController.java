package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.service.CustomerService;
import com.example.demo.service.DriverService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.ShipmentService;
import com.example.demo.service.VehicleService;
import com.example.demo.service.WarehouseService;
import com.example.demo.service.RouteService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminDashboardController {

    private final ShipmentService shipmentService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;
    private final DriverService driverService;
    private final NotificationService notificationService;
    private final WarehouseService warehouseService;
    private final RouteService routeService;

    public AdminDashboardController(ShipmentService shipmentService,
                                    VehicleService vehicleService,
                                    CustomerService customerService,
                                    DriverService driverService,
                                    NotificationService notificationService,
                                    WarehouseService warehouseService,
                                    RouteService routeService) {
        this.shipmentService = shipmentService;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.driverService = driverService;
        this.notificationService = notificationService;
        this.warehouseService = warehouseService;
        this.routeService = routeService;
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // Verify user logged in and has ROLE_ADMIN
        if (loggedInUser == null || loggedInUser.getRole() != UserRole.ADMIN) {
            return "redirect:/login";
        }

        model.addAttribute("user", loggedInUser);

        // Core Dashboard Statistics
        long totalShipments = shipmentService.getTotalShipments();
        long deliveredShipments = shipmentService.getDeliveredShipments();
        long pendingShipments = shipmentService.getPendingShipments();
        long inTransitShipments = shipmentService.getInTransitShipments();

        model.addAttribute("totalCustomers", customerService.getTotalCustomers());
        model.addAttribute("totalDrivers", driverService.getTotalDrivers());
        model.addAttribute("totalShipments", totalShipments);
        model.addAttribute("deliveredShipments", deliveredShipments);
        model.addAttribute("pendingShipments", pendingShipments);
        
        // Compute cancelled shipments (fallback of active minus transit, delivered, and pending)
        long cancelled = totalShipments - deliveredShipments - pendingShipments - inTransitShipments;
        model.addAttribute("cancelledShipments", cancelled > 0 ? cancelled : 0);

        // Recent listings (Shipments, Customers, Drivers)
        model.addAttribute("recentShipments", shipmentService.getRecentShipments());

        List<?> allCustomers = customerService.getAllCustomers();
        model.addAttribute("recentCustomers", allCustomers.stream().limit(5).toList());

        List<?> allDrivers = driverService.getAllDrivers();
        model.addAttribute("recentDrivers", allDrivers.stream().limit(5).toList());

        // System Notification list
        model.addAttribute("latestNotifications", notificationService.getLatestNotifications());

        // Vehicle metadata for charts
        model.addAttribute("activeVehicles", vehicleService.getActiveVehicleCount());
        model.addAttribute("inServiceVehicles", vehicleService.getInServiceVehicleCount());
        model.addAttribute("maintenanceVehicles", vehicleService.getMaintenanceVehicleCount());
        model.addAttribute("totalWarehouses", warehouseService.getAllWarehouses().size());
        model.addAttribute("activeWarehouses", warehouseService.getActiveWarehouseCount());
        model.addAttribute("totalRoutes", routeService.getTotalRoutes());
        model.addAttribute("activeRoutes", routeService.getActiveRoutes());

        return "admin/dashboard/index";
    }

}
