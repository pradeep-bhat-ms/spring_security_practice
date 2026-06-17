package com.security.security.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtService {
	private static String secretkey="";
	
	public jwtService() {
	
	try {
		KeyGenerator instance =KeyGenerator.getInstance("HmacSHA256");
		SecretKey key=instance.generateKey();
		secretkey=Base64.getEncoder().encodeToString(key.getEncoded());
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	}

	public static String generateToken( String username ) {
		Map<String, Object> map=new HashMap<String, Object>();
		return Jwts.builder()
				.claims()
				.add(map)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*60*30))
				.and()
				.signWith(getkey())
				.compact();

	}

	private static Key getkey() {
		byte[] decode=Base64.getDecoder().decode(secretkey);
		return Keys.hmacShaKeyFor(decode);
	}

}
