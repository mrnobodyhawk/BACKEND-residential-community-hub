package com.communityhub.notificationservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Date;

@Table(name = "notification_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_sequence")
    @SequenceGenerator(name = "notification_sequence", sequenceName = "notification_sequence", initialValue = 1000)
    @Column(name = "notification_id")
	private Long notificationId;

	@Column(name = "notification_title")
	private String notificationTitle;

	@Column(name = "notification_description")
	private String notificationDescription;

	@Column(name = "date_of_post")
	private Date dateOfPost;

	@Column(name = "admin_id")
	private Long adminId;

	@Column(name = "posted_by")
	private String postedBy;
	

}