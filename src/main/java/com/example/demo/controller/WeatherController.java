package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.entity.Weather;
import com.example.demo.service.WeatherService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WeatherController {

    // ==========================================
    // Service
    // ==========================================

    private final WeatherService weatherService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public WeatherController(WeatherService weatherService) {

        this.weatherService = weatherService;

    }

    // ==========================================
    // Weather List
    // ==========================================

    @GetMapping("/weather")
    public String weatherList(HttpSession session,
                              Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        // Get Weather List

        List<Weather> weatherList =
                weatherService.getAllWeather();

        model.addAttribute("user", loggedInUser);

        model.addAttribute("weatherList", weatherList);

        // Dashboard Statistics

        model.addAttribute(
                "totalCities",
                weatherService.getTotalCities());

        model.addAttribute(
                "highRisk",
                weatherService.getHighRiskCount());

        model.addAttribute(
                "mediumRisk",
                weatherService.getMediumRiskCount());

        model.addAttribute(
                "lowRisk",
                weatherService.getLowRiskCount());

        model.addAttribute(
                "safeRoutes",
                weatherService.getSafeRouteCount());

        model.addAttribute(
                "blockedRoutes",
                weatherService.getBlockedRouteCount());

        return "weather/list";

    }
    // ==========================================
    // Open Add Weather Page
    // ==========================================

    @GetMapping("/weather/add")
    public String addWeatherPage(HttpSession session,
                                 Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        model.addAttribute("user", loggedInUser);

        model.addAttribute("weather", new Weather());

        return "weather/add";

    }

    // ==========================================
    // Save Weather
    // ==========================================

    @PostMapping("/weather/save")
    public String saveWeather(@ModelAttribute Weather weather,
                              HttpSession session,
                              Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        // Check Duplicate City

        if (weatherService.cityExists(weather.getCity())) {

            model.addAttribute("user", loggedInUser);

            model.addAttribute("weather", weather);

            model.addAttribute(
                    "error",
                    "Weather data for this city already exists.");

            return "weather/add";

        }

        // Default Values

        if (weather.getRiskLevel() == null ||
                weather.getRiskLevel().isBlank()) {

            weather.setRiskLevel("LOW");

        }

        if (weather.getRouteStatus() == null ||
                weather.getRouteStatus().isBlank()) {

            weather.setRouteStatus("SAFE");

        }

        if (weather.getAiSuggestion() == null ||
                weather.getAiSuggestion().isBlank()) {

            weather.setAiSuggestion("No issues detected.");

        }

        // Save

        weatherService.saveWeather(weather);

        return "redirect:/weather";

    }
    // ==========================================
    // Open Edit Weather Page
    // ==========================================

    @GetMapping("/weather/view/{id}")
    public String viewWeather(@PathVariable Long id, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";
        Weather weather = weatherService.getWeatherById(id);
        if (weather == null) return "redirect:/weather";
        model.addAttribute("user", loggedInUser);
        model.addAttribute("weather", weather);
        return "weather/view";
    }

    @GetMapping("/weather/edit/{id}")
    public String editWeatherPage(@PathVariable Long id,
                                  HttpSession session,
                                  Model model) {

        // Check Login

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        Weather weather =
                weatherService.getWeatherById(id);

        model.addAttribute("user", loggedInUser);

        model.addAttribute("weather", weather);

        return "weather/edit";

    }

    // ==========================================
    // Update Weather
    // ==========================================

    @PostMapping("/weather/update")
    public String updateWeather(@ModelAttribute Weather weather,
                                HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        weatherService.updateWeather(weather);

        return "redirect:/weather";

    }

    // ==========================================
    // Delete Weather
    // ==========================================

    @GetMapping("/weather/delete/{id}")
    public String deleteWeather(@PathVariable Long id,
                                HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "redirect:/login";

        }

        weatherService.deleteWeather(id);

        return "redirect:/weather";

    }

}
