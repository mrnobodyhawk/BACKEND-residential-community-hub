package com.communityhub.maintenanceService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "MAINTENANCE_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class MaintenanceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintenance_sequence")
    @SequenceGenerator(name = "maintenance_sequence", sequenceName = "maintenance_sequence", initialValue = 1000)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "requester_name")
    private String requesterName;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "requester_mobile_number")
    private String requesterNumber;

    @Column(name = "request_heading")
    private String requestHeading;

    @Column(name = "description")
    private String description;

    @Column(name = "date_of_Issue")
    private Date dateOfIssue;

    @Column(name = "status")
    private String status;
}



