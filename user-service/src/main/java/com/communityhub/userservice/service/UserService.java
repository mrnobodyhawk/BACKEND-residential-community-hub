package com.communityhub.userservice.service;

import java.util.List;

import com.communityhub.userservice.model.User;

public interface UserService {
    Long signIn(String username, String password);
    String signUp(User user);
	List<User> getAllUsers();
	String getUserFullName(Long userId);
	boolean deleteUser(Long userId);
	List<User> getUser(Long userId);
	String checkUsername(String username);
}