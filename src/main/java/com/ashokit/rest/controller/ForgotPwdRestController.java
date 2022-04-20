package com.ashokit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.service.UserMgmtService;

@RestController
public class ForgotPwdRestController {

	@Autowired
	private UserMgmtService userMgmtService;

	@GetMapping("/forgotPwd/{emailId}")
	public ResponseEntity<String> forgotPwd(@PathVariable String emailId) {
		String forgetPwd = userMgmtService.forgotPwd(emailId);
		return new ResponseEntity<String>(forgetPwd, HttpStatus.OK);
	}
}
