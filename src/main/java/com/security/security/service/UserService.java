package com.security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.security.security.entity.userEntity;
import com.security.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private jwtService jwtService;	

		
	public userEntity saveUser(userEntity u) {
		u.setPassword(encoder.encode(u.getPassword()));
		return userRepository.save(u);
	}

	public String verify(userEntity entity) {		
		Authentication authenticate=(Authentication) manager.authenticate(new UsernamePasswordAuthenticationToken(entity.getUsername(), entity.getPassword()));
		if (authenticate.isAuthenticated()) {
		    return jwtService.generateToken(entity.getUsername());
		}

		return "Login Failed";
	}

}
