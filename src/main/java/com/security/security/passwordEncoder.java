package com.security.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordEncoder {
public static void main(String[] args) {
	BCryptPasswordEncoder cryptPasswordEncoder=new BCryptPasswordEncoder();
	System.out.println(cryptPasswordEncoder.encode("admin@123"));
	System.out.println(cryptPasswordEncoder.encode("user@123"));
}
}
