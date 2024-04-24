package com.communityhub.notificationservice.controller;

import com.communityhub.notificationservice.model.Notification;
import com.communityhub.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communityhub/user/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getAll")
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @PutMapping("/update/{notificationId}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long notificationId, @RequestBody Notification notificationDetails) {
        Notification updatedNotification = notificationService.updateNotification(notificationId, notificationDetails);
        return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{notificationId}")
    public String deleteNotification(@PathVariable Long notificationId) {
        try {
            notificationService.deleteNotification(notificationId);
            return "Notification with ID " + notificationId + " has been deleted successfully.";
        } catch (RuntimeException ex) {
            return "Notification with ID " + notificationId + " does not exist.";
        }
    }
}
