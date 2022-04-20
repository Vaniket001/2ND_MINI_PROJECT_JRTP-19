package com.ashokit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.bindings.LoginForm;
import com.ashokit.service.UserMgmtService;

@RestController
public class LoginRestController {

	@Autowired
	private UserMgmtService userMgmtService;

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginForm form) {
		String status = userMgmtService.loginCheck(form);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
}
