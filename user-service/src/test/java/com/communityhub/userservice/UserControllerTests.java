package com.communityhub.userservice;

import com.communityhub.userservice.controller.UserController;
import com.communityhub.userservice.dto.SignInRequest;
import com.communityhub.userservice.exception.*;
import com.communityhub.userservice.model.User;
import com.communityhub.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSignIn_UserFoundAndCorrectPassword() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("testUser");
        signInRequest.setPassword("password");
        Long expectedUserId = 1L;

        when(userService.signIn("testUser", "password")).thenReturn(expectedUserId);

        assertEquals(expectedUserId, userController.signIn(signInRequest));
    }

    @Test
    void testSignIn_UserNotFound() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("nonExistingUser");
        signInRequest.setPassword("password");

        when(userService.signIn("nonExistingUser", "password")).thenThrow(new UserNotFoundException("Username not found"));

        assertEquals(0L, userController.signIn(signInRequest));
    }

    @Test
    void testSignIn_IncorrectPassword() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("testUser");
        signInRequest.setPassword("wrongPassword");

        when(userService.signIn("testUser", "wrongPassword")).thenThrow(new InvalidPasswordException("Invalid password"));

        assertEquals(0L, userController.signIn(signInRequest));
    }

    @Test
    void testSignUp_Successful() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("password");

        when(userService.signUp(user)).thenReturn("Successfully Signed Up");

        assertEquals("Successfully Signed Up", userController.signUp(user));
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userService.getAllUsers()).thenReturn(users);

        assertEquals(users, userController.getAllUsers());
    }

    @Test
    void testGetUser() {
        Long userId = 1L;
        List<User> expectedUser = new ArrayList<>();
        expectedUser.add(new User());
        
        when(userService.getUser(userId)).thenReturn(expectedUser);

        assertEquals(expectedUser, userController.getUser(userId));
    }

    @Test
    void testCheckUsername_UsernameExists() {
        String username = "existingUser";

        when(userService.checkUsername(username)).thenReturn("Username already exist");

        assertEquals("Username already exist", userController.checkUsername(username));
    }

    @Test
    void testCheckUsername_UsernameDoesNotExist() {
        String username = "nonExistingUser";

        when(userService.checkUsername(username)).thenReturn("None");

        assertEquals("None", userController.checkUsername(username));
    }

    @Test
    void testGetUserFullName_UserFound() {
        Long userId = 1L;
        String expectedFullName = "John Doe";

        when(userService.getUserFullName(userId)).thenReturn(expectedFullName);

        assertEquals(expectedFullName, userController.getUserFullName(userId));
    }

    @Test
    void testGetUserFullName_UserNotFound() {
        Long userId = 1L;

        when(userService.getUserFullName(userId)).thenThrow(new UserNotFoundException("User not found"));

        assertEquals("User not found", userController.getUserFullName(userId));
    }

    @Test
    void testDeleteUser_UserFoundAndDeleted() {
        Long userId = 1L;

        when(userService.deleteUser(userId)).thenReturn(true);

        assertEquals("User with ID 1 has been successfully deleted.", userController.deleteUser(userId));
    }

    @Test
    void testDeleteUser_UserNotFound() {
        Long userId = 1L;

        when(userService.deleteUser(userId)).thenReturn(false);

        assertEquals("User with ID 1 does not exist.", userController.deleteUser(userId));
    }
}
