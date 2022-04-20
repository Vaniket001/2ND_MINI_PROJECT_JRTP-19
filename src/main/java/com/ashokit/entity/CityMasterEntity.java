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
@Table(name="CITY_MASTER")
public class CityMasterEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="CITY_NAME")
	private String cityName;

}
