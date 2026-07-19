package com.example.demo.service.impl;

import com.example.demo.dto.GenAiAnalyzeRequest;
import com.example.demo.dto.GenAiAnalyzeResponse;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.Weather;
import com.example.demo.service.GenAiAnalysisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GenAiAnalysisServiceImpl implements GenAiAnalysisService {
    private final RestClient client;
    public GenAiAnalysisServiceImpl(@Value("${genai.base-url:http://localhost:8000}") String baseUrl) { this.client = RestClient.builder().baseUrl(baseUrl).build(); }
    @Override public GenAiAnalyzeResponse analyze(Shipment shipment, Weather weather, String currentRoute, String customerName) {
        GenAiAnalyzeRequest request = new GenAiAnalyzeRequest(
            new GenAiAnalyzeRequest.Weather(weather.getCity(), weather.getTemperature(), weather.getHumidity(), weather.getWindSpeed(), weather.getVisibility(), weather.getWeatherCondition()),
            new GenAiAnalyzeRequest.Shipment(shipment.getTrackingNumber(), shipment.getSource(), shipment.getDestination(), currentRoute),
            new GenAiAnalyzeRequest.Customer(customerName));
        return client.post().uri("/analyze").contentType(MediaType.APPLICATION_JSON).body(request).retrieve().body(GenAiAnalyzeResponse.class);
    }
}
