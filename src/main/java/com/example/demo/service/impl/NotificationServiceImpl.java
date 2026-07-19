package com.example.demo.service.impl;

import com.example.demo.entity.Notification;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public Notification getNotificationByNotificationId(String notificationId) {
        return notificationRepository.findByNotificationId(notificationId);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    @Override
    public List<Notification> getNotificationsByRecipient(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }

    @Override
    public List<Notification> getNotificationsByRecipientType(String recipientType) {
        return notificationRepository.findByRecipientType(recipientType);
    }

    @Override
    public List<Notification> getNotificationsByStatus(String status) {
        return notificationRepository.findByStatus(status);
    }

    @Override
    public List<Notification> getNotificationsByPriority(String priority) {
        return notificationRepository.findByPriority(priority);
    }

    @Override
    public List<Notification> getNotificationsByType(String notificationType) {
        return notificationRepository.findByNotificationType(notificationType);
    }

    @Override
    public List<Notification> getNotificationsByShipment(String shipmentId) {
        return notificationRepository.findByShipmentId(shipmentId);
    }

    @Override
    public List<Notification> getUnreadNotifications() {
        return notificationRepository.findByIsRead(false);
    }

    @Override
    public List<Notification> getLatestNotifications() {
        return notificationRepository.findTop10ByOrderByCreatedAtDesc();
    }
    @Override
    public void markAsRead(Long id) {

        Notification notification = getNotificationById(id);

        if (notification != null) {

            notification.setIsRead(true);
            notification.setReadAt(java.time.LocalDateTime.now());

            notificationRepository.save(notification);

        }

    }

    @Override
    public void markEmailSent(Long id) {

        Notification notification = getNotificationById(id);

        if (notification != null) {

            notification.setEmailSent(true);
            notification.setSentAt(java.time.LocalDateTime.now());

            notificationRepository.save(notification);

        }

    }

    @Override
    public void markSmsSent(Long id) {

        Notification notification = getNotificationById(id);

        if (notification != null) {

            notification.setSmsSent(true);

            notificationRepository.save(notification);

        }

    }

    @Override
    public void markPushSent(Long id) {

        Notification notification = getNotificationById(id);

        if (notification != null) {

            notification.setPushSent(true);

            notificationRepository.save(notification);

        }

    }

}