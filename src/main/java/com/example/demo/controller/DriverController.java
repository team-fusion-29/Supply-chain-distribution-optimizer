package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.entity.Driver;
import com.example.demo.service.DriverService;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    // ==========================================
    // Service
    // ==========================================

    private final DriverService driverService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public DriverController(DriverService driverService) {

        this.driverService = driverService;

    }

    // ==========================================
    // Driver List
    // ==========================================

    @GetMapping
    public String driverList(@RequestParam(required = false) String search,
                             @RequestParam(required = false) String status, Model model) {

        java.util.List<Driver> drivers = driverService.searchDrivers(search);
        if (status != null && !status.isBlank()) drivers = drivers.stream()
                .filter(driver -> status.equalsIgnoreCase(driver.getStatus())).toList();
        model.addAttribute("drivers", drivers);

        model.addAttribute("totalDrivers",
                driverService.getTotalDrivers());

        model.addAttribute("availableDrivers",
                driverService.getAvailableDriversCount());

        model.addAttribute("onTripDrivers",
                driverService.getOnTripDriversCount());

        model.addAttribute("offlineDrivers",
                driverService.getOfflineDriversCount());

        model.addAttribute("onLeaveDrivers",
                driverService.getOnLeaveDriversCount());

        return "driver/list";

    }

    // ==========================================
    // Show Add Driver Page
    // ==========================================

    @GetMapping("/add")
    public String showAddDriverPage(Model model) {

        model.addAttribute("driver", new Driver());

        return "driver/add";

    }

    // ==========================================
    // Save Driver
    // ==========================================

    @PostMapping("/save")
    public String saveDriver(@Valid @ModelAttribute Driver driver, BindingResult result, Model model) {

        if (driverService.driverIdExists(driver.getDriverId())) result.rejectValue("driverId", "duplicate", "Driver ID already exists.");
        if (driverService.emailExists(driver.getEmail())) result.rejectValue("email", "duplicate", "Email already exists.");
        if (driverService.licenseNumberExists(driver.getLicenseNumber())) result.rejectValue("licenseNumber", "duplicate", "Licence number already exists.");
        if (result.hasErrors()) return "driver/add";

        driverService.saveDriver(driver);

        return "redirect:/drivers";

    }
    // ==========================================
    // Show Edit Driver Page
    // ==========================================

    @GetMapping("/edit/{id}")
    public String showEditDriverPage(@PathVariable Long id, Model model) {

        Driver driver = driverService.getDriverById(id);

        if (driver == null) {

            return "redirect:/drivers";

        }

        model.addAttribute("driver", driver);

        return "driver/edit";

    }

    // ==========================================
    // Update Driver
    // ==========================================

    @PostMapping("/update")
    public String updateDriver(@Valid @ModelAttribute Driver driver, BindingResult result) {

        if (result.hasErrors()) return "driver/edit";

        driverService.updateDriver(driver);

        return "redirect:/drivers";

    }

    // ==========================================
    // Delete Driver
    // ==========================================

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable Long id) {

        driverService.deleteDriver(id);

        return "redirect:/drivers";

    }

    // ==========================================
    // View Driver Details
    // ==========================================

    @GetMapping("/view/{id}")
    public String viewDriver(@PathVariable Long id, Model model) {

        Driver driver = driverService.getDriverById(id);

        if (driver == null) {

            return "redirect:/drivers";

        }

        model.addAttribute("driver", driver);

        return "driver/view";

    }
    // ==========================================
    // Search Driver By Status
    // ==========================================

    @GetMapping("/status/{status}")
    public String getDriversByStatus(@PathVariable String status,
                                     Model model) {

        model.addAttribute("drivers",
                driverService.getDriversByStatus(status));

        return "driver/list";

    }

    // ==========================================
    // Search Driver By City
    // ==========================================

    @GetMapping("/city/{city}")
    public String getDriversByCity(@PathVariable String city,
                                   Model model) {

        model.addAttribute("drivers",
                driverService.getDriversByCity(city));

        return "driver/list";

    }

    // ==========================================
    // Search Driver By State
    // ==========================================

    @GetMapping("/state/{state}")
    public String getDriversByState(@PathVariable String state,
                                    Model model) {

        model.addAttribute("drivers",
                driverService.getDriversByState(state));

        return "driver/list";

    }

    // ==========================================
    // Available Drivers
    // ==========================================

    @GetMapping("/available")
    public String getAvailableDrivers(Model model) {

        model.addAttribute("drivers",
                driverService.getAvailableDrivers());

        return "driver/list";

    }

}
