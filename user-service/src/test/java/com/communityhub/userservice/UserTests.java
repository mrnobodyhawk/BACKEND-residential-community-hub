package com.communityhub.userservice;

import org.junit.jupiter.api.Test;

import com.communityhub.userservice.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {

    @Test
    void testUserCreation() {
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("Prince");
        user.setLastName("Kumar");
        user.setUsername("princek");
        user.setPassword("password");
        user.setMobileNumber("1234567890");
        user.setPresentAddress("123 Main St");
        user.setUserType("normal");

        assertEquals(1L, user.getUserId());
        assertEquals("Prince", user.getFirstName());
        assertEquals("Kumar", user.getLastName());
        assertEquals("princek", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("1234567890", user.getMobileNumber());
        assertEquals("123 Main St", user.getPresentAddress());
        assertEquals("normal", user.getUserType());
    }
}
