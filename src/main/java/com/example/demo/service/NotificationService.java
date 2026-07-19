package com.example.demo.service;

import com.example.demo.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification saveNotification(Notification notification);

    Notification updateNotification(Notification notification);

    void deleteNotification(Long id);

    Notification getNotificationById(Long id);

    Notification getNotificationByNotificationId(String notificationId);

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByRecipient(Long recipientId);

    List<Notification> getNotificationsByRecipientType(String recipientType);

    List<Notification> getNotificationsByStatus(String status);

    List<Notification> getNotificationsByPriority(String priority);

    List<Notification> getNotificationsByType(String notificationType);

    List<Notification> getNotificationsByShipment(String shipmentId);

    List<Notification> getUnreadNotifications();

    List<Notification> getLatestNotifications();

    void markAsRead(Long id);

    void markEmailSent(Long id);

    void markSmsSent(Long id);

    void markPushSent(Long id);

}