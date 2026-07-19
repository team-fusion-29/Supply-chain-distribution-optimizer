package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "analysis_history")
public class AnalysisHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long shipmentId;
    private Long weatherId;
    private String routeCode;
    private String customerEmail;
    @Lob @Column(columnDefinition = "TEXT") private String weatherAnalysis;
    @Lob @Column(columnDefinition = "TEXT") private String riskAnalysis;
    @Lob @Column(columnDefinition = "TEXT") private String routeAnalysis;
    @Lob @Column(columnDefinition = "TEXT") private String etaPrediction;
    @Lob @Column(columnDefinition = "TEXT") private String generatedEmail;
    private String status;
    @Column(length = 1000) private String errorMessage;
    private LocalDateTime createdAt;
    @PrePersist void created() { createdAt = LocalDateTime.now(); if (status == null) status = "SUCCESS"; }
    public Long getId(){return id;} public void setShipmentId(Long v){shipmentId=v;} public void setWeatherId(Long v){weatherId=v;} public void setRouteCode(String v){routeCode=v;} public void setCustomerEmail(String v){customerEmail=v;} public void setWeatherAnalysis(String v){weatherAnalysis=v;} public void setRiskAnalysis(String v){riskAnalysis=v;} public void setRouteAnalysis(String v){routeAnalysis=v;} public void setEtaPrediction(String v){etaPrediction=v;} public void setGeneratedEmail(String v){generatedEmail=v;} public void setStatus(String v){status=v;} public void setErrorMessage(String v){errorMessage=v;}
}
