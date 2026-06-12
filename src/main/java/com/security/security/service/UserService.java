package com.security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.security.entity.userEntity;
import com.security.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private PasswordEncoder encoder;
	

		
	public userEntity saveUser(userEntity u) {
		u.setPassword(encoder.encode(u.getPassword()));
		return userRepository.save(u);
	}

}
