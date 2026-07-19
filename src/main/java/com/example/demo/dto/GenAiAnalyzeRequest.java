package com.example.demo.dto;
public record GenAiAnalyzeRequest(Weather weather, Shipment shipment, Customer customer) {
 public record Weather(String city, double temperature, int humidity, double wind_speed, double visibility, String condition) { }
 public record Shipment(String shipment_id, String source, String destination, String current_route) { }
 public record Customer(String name) { }
}
