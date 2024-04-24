package com.communityhub.visitorService.model;

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
@Table(name = "visitor_entry")
@Getter
@Setter
@NoArgsConstructor
public class VisitorEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitor_sequence")
    @SequenceGenerator(name = "visitor_sequence", sequenceName = "visitor_sequence", initialValue = 1000)
    @Column(name = "visitor_id")
    private Long visitorId;

	@Column(name = "user_id")
    private Long userId;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "visitor_full_name")
    private String visitorFullName;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "check_in_date")
    private Date checkInDate;

    @Column(name = "check_out_date")
    private Date checkOutDate;

	
}
