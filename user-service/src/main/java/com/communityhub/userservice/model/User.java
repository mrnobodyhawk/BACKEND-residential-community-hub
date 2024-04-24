package com.communityhub.userservice.model;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "data_table")
@Entity
public class User {
	
    @Id
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "mobile_number")
    private String mobileNumber;
    
    @Column(name = "present_address")
    private String presentAddress;
    
    @Column(name = "user_type")
    private String userType;
}


