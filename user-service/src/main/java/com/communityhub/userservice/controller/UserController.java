package com.communityhub.userservice.controller;

import com.communityhub.userservice.dto.SignInRequest;
import com.communityhub.userservice.exception.*;
import com.communityhub.userservice.model.User;
import com.communityhub.userservice.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communityhub/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public Long signIn(@RequestBody SignInRequest signInRequest) {
        try {
            return userService.signIn(signInRequest.getUsername(), signInRequest.getPassword());
        } catch (UserNotFoundException e) {
            return (long) 0;
        } catch (InvalidPasswordException | InvalidUsernameException e) {
            return (long) 0;
        } catch (Exception e) { 
            return (long) 0;
        }
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody User user) {
        try {
            return userService.signUp(user);
        } catch (UserAlreadyExistException | InvalidPasswordException | InvalidUsernameException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An error occurred during sign-up process";
        }
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            return null;
        }
    }
    
    @GetMapping("/getUser/{userId}")
    public List<User> getUser(@PathVariable Long userId) {
        try {
            return userService.getUser(userId);
        } catch (Exception e) {
            return null;
        }
    }
    
    @GetMapping("/username/{username}")
    public String checkUsername(@PathVariable String username) {
        try {
            return userService.checkUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/getUserFullName/{userId}")
    public String getUserFullName(@PathVariable Long userId) {
        try {
            return userService.getUserFullName(userId);
        } catch (UserNotFoundException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An error occurred while fetching user full name";
        }
    }
    
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return "User with ID " + userId + " has been successfully deleted.";
        } else {
            return "User with ID " + userId + " does not exist.";
        }
    }
}
