package com.communityhub.userservice.service;

import com.communityhub.userservice.exception.*;
import com.communityhub.userservice.model.User;
import com.communityhub.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Override 
    public Long signIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("Username not found");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        } else {
//            return "SUCCESSFULLY SIGNED IN";
        	return user.getUserId();
        }
    }

    /*
    @Override
    public String signUp(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User already exists");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new InvalidUsernameException("Invalid username");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new InvalidPasswordException("Invalid password");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "Successfully Signed Up";
    }
    */
    
    @Override
    public String signUp(User user) {
    	if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User already exists");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new InvalidUsernameException("Invalid username");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new InvalidPasswordException("Invalid password");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "Successfully Signed Up";
    }
    
    @Override
    public List<User> getAllUsers(){
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching users");
        }
    }
    
    @Override
    public String checkUsername(String username){
    	User user = userRepository.findByUsername(username);
        if (user == null) {
        	return "None";
        }else {
        	return "Username already exist";
        }
    }
    
    @Override
    public List<User> getUser(Long userId){
    	try {
    		return userRepository.findByUserId(userId);
    	}catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching users");
        }
    }
    
    @Override
    public String getUserFullName(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        } else {
            return user.getFirstName() + " " + user.getLastName();
        }
    }
    
    @Override
    public boolean deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }
}
