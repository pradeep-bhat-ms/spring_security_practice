package com.security.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.security.entity.userEntity;
import com.security.security.service.UserService;

@RestController
public class homecontroller {
//
//	@GetMapping("/")
//	public String home() {
//		return "Spring Security";
//	}
////	@GetMapping("/public")
////	public String user() {
////		return "this is public";
////	}
////	@GetMapping("/private")
////	public String admin() {
////		return "this is private ";
////	}
//	 @GetMapping("/admin")
//	    public String admin() {
//	    	return "Admin Login";
//	    }
//	    
//	    @GetMapping("/user")
//	    public String user() {
//	    	return "User Login";
//	
//}
	
	@Autowired
	private UserService us;
	
	@PostMapping("/save")
	public userEntity saveUser(@RequestBody userEntity u) {
		return us.saveUser(u);
	}
}