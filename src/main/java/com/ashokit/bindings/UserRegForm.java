package com.ashokit.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegForm {
	
	private  String fName;
	private String lName;
	private String email;
	private String pazzword;
	private String accStatus;
	private Long phno;
	private LocalDate dob;
	private String gender;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	
	

}
