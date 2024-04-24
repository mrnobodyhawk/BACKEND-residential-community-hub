package com.communityhub.notificationservice.service;

import com.communityhub.notificationservice.model.Notification;
import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotifications();
    Notification createNotification(Notification notification);
    Notification updateNotification(Long notificationId, Notification notificationDetails);
    void deleteNotification(Long notificationId);
}