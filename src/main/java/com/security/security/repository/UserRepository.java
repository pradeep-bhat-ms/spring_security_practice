package com.security.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.security.entity.userEntity;

public interface UserRepository extends JpaRepository<userEntity, Integer>{
	

	Optional<userEntity> findByusername(String username);

}
