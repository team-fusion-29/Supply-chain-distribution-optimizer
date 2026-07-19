package com.example.demo.service.impl;

import com.example.demo.entity.Contact;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.User;
import com.example.demo.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    public EmailServiceImpl(JavaMailSender mailSender) { this.mailSender = mailSender; }
    public void sendContactAcknowledgement(Contact contact) { send(contact.getEmail(), "We received your message", "Hello " + contact.getFullName() + ",\n\nWe received your message and will respond shortly."); }
    public void sendContactReply(Contact contact) { send(contact.getEmail(), "Reply to your LogiSphere enquiry", contact.getReplyMessage()); }
    public void sendRegistrationEmail(User user) { send(user.getEmail(), "Welcome to LogiSphere", "Hello " + user.getFirstName() + ",\n\nYour account has been created successfully."); }
    public void sendShipmentStatusEmail(String recipientEmail, Shipment shipment) { send(recipientEmail, "Shipment " + shipment.getTrackingNumber() + " update", "Your shipment status is " + shipment.getStatus() + "."); }
    public void sendGeneratedAnalysisEmail(String recipientEmail, String subject, String content) { send(recipientEmail, subject, content); }
    private void send(String to, String subject, String text) {
        try { SimpleMailMessage message = new SimpleMailMessage(); message.setTo(to); message.setSubject(subject); message.setText(text); mailSender.send(message); }
        catch (Exception ignored) { /* Email is best-effort; primary database operation remains successful. */ }
    }
}
