package com.communityhub.notificationservice;


import com.communityhub.notificationservice.feign.UserInterface;
import com.communityhub.notificationservice.model.Notification;
import com.communityhub.notificationservice.repository.NotificationRepository;
import com.communityhub.notificationservice.service.NotificationServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotificationServiceImplTests {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserInterface userInterface;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    void testGetAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification());
        when(notificationRepository.findAll()).thenReturn(notifications);
        List<Notification> result = notificationService.getAllNotifications();
        assertEquals(1, result.size());
    }

    @Test
    void testCreateNotification() {
        Notification notification = new Notification();
        notification.setAdminId(1L);
        when(userInterface.getUserFullName(anyLong())).thenReturn("Admin Name");
        when(notificationRepository.save(notification)).thenReturn(notification);
        Notification result = notificationService.createNotification(notification);
        assertEquals("Admin Name", result.getPostedBy());
        verify(notificationRepository, times(1)).save(notification);
    }

    @Test
    void testUpdateNotification() {
        Long notificationId = 1L;
        Notification existingNotification = new Notification();
        existingNotification.setNotificationTitle("Old Title");
        Notification updatedNotification = new Notification();
        updatedNotification.setNotificationTitle("New Title");
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(existingNotification));
        when(notificationRepository.save(existingNotification)).thenReturn(existingNotification);
        Notification result = notificationService.updateNotification(notificationId, updatedNotification);
        assertEquals("New Title", result.getNotificationTitle());
        verify(notificationRepository, times(1)).save(existingNotification);
    }

    @Test
    void testDeleteNotification() {
        Long notificationId = 1L;
        when(notificationRepository.existsById(notificationId)).thenReturn(true);
        notificationService.deleteNotification(notificationId);
        verify(notificationRepository, times(1)).deleteById(notificationId);
    }
}
