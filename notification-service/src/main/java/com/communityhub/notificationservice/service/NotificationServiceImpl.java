package com.communityhub.notificationservice.service;

import com.communityhub.notificationservice.feign.UserInterface;
import com.communityhub.notificationservice.model.Notification;
import com.communityhub.notificationservice.repository.NotificationRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserInterface userInterface;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification createNotification(Notification notification) {
        Long userId = notification.getAdminId();
        String postedBy = "" + userInterface.getUserFullName(userId);

        // Set postedBy field
        notification.setPostedBy(postedBy);

        // Save notification
        return notificationRepository.save(notification);
    }

    

    @Override
    public Notification updateNotification(Long notificationId, Notification notificationDetails) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
            Notification existingNotification = optionalNotification.get();
            existingNotification.setNotificationTitle(notificationDetails.getNotificationTitle());
            existingNotification.setNotificationDescription(notificationDetails.getNotificationDescription());
            existingNotification.setDateOfPost(notificationDetails.getDateOfPost());
            return notificationRepository.save(existingNotification);
        } else {
            throw new RuntimeException("Notification not found with id: " + notificationId);
        }
    }

    @Override
    public void deleteNotification(Long notificationId) {
        if (notificationRepository.existsById(notificationId)) {
            notificationRepository.deleteById(notificationId);
        } else {
            throw new RuntimeException("Notification not found with id: " + notificationId);
        }
    }
}
