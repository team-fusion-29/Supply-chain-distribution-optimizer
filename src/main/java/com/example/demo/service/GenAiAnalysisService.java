package com.example.demo.service;

import com.example.demo.dto.GenAiAnalyzeResponse;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.Weather;

public interface GenAiAnalysisService {
    GenAiAnalyzeResponse analyze(Shipment shipment, Weather weather, String currentRoute, String customerName);
}
