package com.ashokit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USER_MANAGEMENT")
public class UserDetailsEntity {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "FIRST_NAME")
	private String fName;

	@Column(name = "LAST_NAME")
	private String lName;

	@Column(name = "USER_EMAIL")
	private String email;

	@Column(name = "USER_PWD")
	private String pazzword;

	@Column(name = "USER_MOBILE")
	private Long phon;

	@Column(name = "CREATE_DATE")
	private String dob;

	@Column(name = "USER_GENDER")
	private String gender;

	@Column(name = "CITY_ID")
	private Integer cityId;

	@Column(name = "STATE_ID")
	private Integer stateId;

	@Column(name = "COUNTRY_ID")
	private Integer countryId;

	@Column(name = "ACCOUNT_STATUS")
	private String accStatus;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "UPDATED_DATE", updatable = false)
	@UpdateTimestamp
	private Date updatedDate;

}
