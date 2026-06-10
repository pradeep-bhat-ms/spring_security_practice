package com.security.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homecontroller {

	@GetMapping("/")
	public String home() {
		return "Spring Security";
	}
	@GetMapping("/public")
	public String user() {
		return "this is public";
	}
	@GetMapping("/private")
	public String admin() {
		return "this is private ";
	}
}