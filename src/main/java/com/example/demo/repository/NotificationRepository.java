package com.example.demo.repository;

import com.example.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Notification findByNotificationId(String notificationId);

    List<Notification> findByRecipientId(Long recipientId);

    List<Notification> findByRecipientType(String recipientType);

    List<Notification> findByStatus(String status);

    List<Notification> findByPriority(String priority);

    List<Notification> findByNotificationType(String notificationType);

    List<Notification> findByShipmentId(String shipmentId);

    List<Notification> findByIsRead(Boolean isRead);

    List<Notification> findByEmailSent(Boolean emailSent);

    List<Notification> findBySmsSent(Boolean smsSent);

    List<Notification> findByPushSent(Boolean pushSent);

    List<Notification> findTop10ByOrderByCreatedAtDesc();

    List<Notification> findTop20ByRecipientIdOrderByCreatedAtDesc(Long recipientId);

}