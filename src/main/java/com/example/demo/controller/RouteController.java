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

import com.example.demo.entity.Route;
import com.example.demo.entity.User;
import com.example.demo.service.RouteService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RouteController {

    // ==========================================
    // Service
    // ==========================================

    private final RouteService routeService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public RouteController(RouteService routeService) {

        this.routeService = routeService;

    }

    // ==========================================
    // View All Routes
    // ==========================================

    @GetMapping("/routes")
    public String routeList(HttpSession session, Model model,
                            @RequestParam(required = false) String search,
                            @RequestParam(required = false) String status) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        // Get Routes

        List<Route> routeList = routeService.searchRoutes(search);
        if (status != null && !status.isBlank()) routeList = routeList.stream()
                .filter(route -> status.equalsIgnoreCase(route.getStatus())).toList();

        model.addAttribute("user", loggedInUser);

        model.addAttribute("routes", routeList);

        // Dashboard Statistics

        model.addAttribute(
                "activeRoutes",
                routeService.getActiveRoutes());

        model.addAttribute(
                "blockedRoutes",
                routeService.getBlockedRoutes());

        model.addAttribute(
                "maintenanceRoutes",
                routeService.getMaintenanceRoutes());

        return "route/list";

    }

    // ==========================================
    // Open Add Route Page
    // ==========================================

    @GetMapping("/routes/add")
    public String addRoutePage(HttpSession session,
                               Model model) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        model.addAttribute(
                "route",
                new Route());

        model.addAttribute(
                "user",
                loggedInUser);

        return "route/add";

    }
    // ==========================================
    // Save Route
    // ==========================================

    @PostMapping("/routes/save")
    public String saveRoute(@Valid @ModelAttribute("route") Route route, BindingResult result,
                            HttpSession session,
                            Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        if (result.hasErrors()) { model.addAttribute("user", loggedInUser); return "route/add"; }
        // Check Duplicate Route Code

        if (routeService.routeCodeExists(
                route.getRouteCode())) {

            model.addAttribute(
                    "user",
                    loggedInUser);

            model.addAttribute(
                    "route",
                    route);

            model.addAttribute(
                    "error",
                    "Route Code already exists.");

            return "route/add";

        }

        // Default Status

        if (route.getStatus() == null ||
                route.getStatus().isBlank()) {

            route.setStatus("ACTIVE");

        }

        // Save Route

        routeService.saveRoute(route);

        return "redirect:/routes";

    }
    // ==========================================
    // Open Edit Route Page
    // ==========================================

    @GetMapping("/routes/view/{id}")
    public String viewRoute(@PathVariable Long id, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";
        Route route = routeService.getRouteById(id);
        if (route == null) return "redirect:/routes";
        model.addAttribute("user", loggedInUser);
        model.addAttribute("route", route);
        return "route/view";
    }

    @GetMapping("/routes/edit/{id}")
    public String editRoutePage(@PathVariable Long id,
                                HttpSession session,
                                Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        Route route =
                routeService.getRouteById(id);

        model.addAttribute(
                "user",
                loggedInUser);

        model.addAttribute(
                "route",
                route);

        return "route/edit";

    }

    // ==========================================
    // Update Route
    // ==========================================

    @PostMapping("/routes/update")
    public String updateRoute(@Valid @ModelAttribute Route route, BindingResult result,
                              HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        if (result.hasErrors()) return "redirect:/routes/edit/" + route.getId();
        routeService.updateRoute(route);

        return "redirect:/routes";

    }

    // ==========================================
    // Delete Route
    // ==========================================

    @GetMapping("/routes/delete/{id}")
    public String deleteRoute(@PathVariable Long id,
                              HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        routeService.deleteRoute(id);

        return "redirect:/routes";

    }

}
