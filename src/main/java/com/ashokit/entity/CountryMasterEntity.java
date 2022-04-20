package com.ashokit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="COUNTRY_MASTER")
public class CountryMasterEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="COUNTRY_CODE")
	private Integer countryCode;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;

}
