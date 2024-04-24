package com.communityhub.notificationservice;

import com.communityhub.notificationservice.model.Notification;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class NotificationTests {

    @Test
    public void testNotificationConstructor() {
        Notification notification = new Notification();
        assertNotNull(notification);
    }

    @Test
    public void testNotificationGettersAndSetters() {
        Notification notification = new Notification();
        notification.setNotificationId(1L);
        notification.setNotificationTitle("Test Title");
        notification.setNotificationDescription("Test Description");
        Date date = new Date();
        notification.setDateOfPost(date);
        notification.setAdminId(123L);
        notification.setPostedBy("Admin");
        assertEquals(1L, notification.getNotificationId());
        assertEquals("Test Title", notification.getNotificationTitle());
        assertEquals("Test Description", notification.getNotificationDescription());
        assertEquals(date, notification.getDateOfPost());
        assertEquals(123L, notification.getAdminId());
        assertEquals("Admin", notification.getPostedBy());
    }
}