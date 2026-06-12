package com.security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.security.entity.userEntity;
import com.security.security.repository.UserRepository;

@Service
public class MyUserService  implements UserDetailsService{

	@Autowired
	UserRepository ur;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		userEntity user=ur.findByusername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
		return User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
	}

}
