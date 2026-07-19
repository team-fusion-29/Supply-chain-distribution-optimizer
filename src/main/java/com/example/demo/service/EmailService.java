package com.example.demo.service;

import com.example.demo.entity.Contact;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.User;

public interface EmailService {
    void sendContactAcknowledgement(Contact contact);
    void sendContactReply(Contact contact);
    void sendRegistrationEmail(User user);
    void sendShipmentStatusEmail(String recipientEmail, Shipment shipment);
    void sendGeneratedAnalysisEmail(String recipientEmail, String subject, String content);
}
