package com.ashokit.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.bindings.UserRegForm;
import com.ashokit.service.UserMgmtService;

@RestController
public class RegistrationRestController {
	
	@Autowired
	private UserMgmtService userMgmtService;
	
	@GetMapping("/emailcheck/{email}")
	public ResponseEntity<String> emailCheck(@PathVariable("email") String emailId){
		String status=userMgmtService.emailCheck(emailId);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@GetMapping("/countries")
	public ResponseEntity<Map<Integer, String>> getCountries(){
		Map<Integer, String> countriesMap=userMgmtService.loadCountries();
		return new ResponseEntity<Map<Integer,String>>(countriesMap, HttpStatus.OK);	
	}
	
	@GetMapping("/states/{countryid}")
	public ResponseEntity<Map<Integer, String>> getStates(Integer countryId){
		Map<Integer, String> statesMap=userMgmtService.loadStates(countryId);
		return new ResponseEntity<Map<Integer,String>>(statesMap, HttpStatus.OK);	
	}
	
	@GetMapping("/cities/{stateid}")
	public ResponseEntity<Map<Integer, String>> getCities(Integer stateId){
		Map<Integer, String> citiesMap=userMgmtService.loadStates(stateId);
		return new ResponseEntity<Map<Integer,String>>(citiesMap, HttpStatus.OK);	
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody UserRegForm user){
		String status= userMgmtService.saveUser(user);
		return new ResponseEntity<String>(status, HttpStatus.CREATED);
	}
}
