package com.ashokit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.bindings.UnlockAccForm;
import com.ashokit.service.UserMgmtService;

@RestController
public class UnlockAccRestController {

	@Autowired
	private UserMgmtService userMgmtService;

	@PostMapping("/unlock")
	public ResponseEntity<String> unlockAcc(@RequestBody UnlockAccForm unlock) {
		String unlockAcc = userMgmtService.unlockAcc(unlock);
		return new ResponseEntity<String>(unlockAcc, HttpStatus.OK);
	}

}
