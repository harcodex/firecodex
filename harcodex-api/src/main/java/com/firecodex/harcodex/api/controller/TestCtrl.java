package com.firecodex.harcodex.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestCtrl {
	
	@RequestMapping(value = "/msg", method = RequestMethod.GET)
	public String message() {
		return "This is a test msg!";
	}
}
