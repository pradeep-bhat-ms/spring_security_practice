package com.security.security.service;

import java.nio.charset.StandardCharsets;
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
	private  String secretkey="1234567890123456789012345678901234567890123456789012345678901234";
//	
//	public jwtService() {
//	try {
//		KeyGenerator instance =KeyGenerator.getInstance("HmacSHA256");
//		SecretKey key=instance.generateKey();
//		secretkey=Base64.getEncoder().encodeToString(key.getEncoded());
//	} catch (NoSuchAlgorithmException e) {
//		e.printStackTrace();
//	}
//	}
//	public  String generateToken( String username ) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		return Jwts.builder()
//				
//				// payload
//				.claims()
//				.add(map)
//				.subject(username)
//				.issuedAt(new Date(System.currentTimeMillis()))
//				.expiration(new Date(System.currentTimeMillis()+1000*60*60*30))
//				.and()
//				
//				// signature
//				.signWith(getkey())
//				.compact();
//
//	}
//	private static Key getkey() {
//		byte[] decode=Base64.getDecoder().decode(secretkey);
//		return Keys.hmacShaKeyFor(decode);
//	}
//	
	 private Key getKey() 
	 {
		 return Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
	 }
	
	// generate token
	 public String generateToken(String username)
	 {
		 return Jwts.builder()
				 .subject(username)
				 .issuedAt(new Date(System.currentTimeMillis()))
				 .expiration(new Date(System.currentTimeMillis()+1000*60*60*12))
				 .signWith(getKey())
				 .compact();
	 }
	 
	// token validation
	// Extract username
	public  String extractUsername(String token)
		{
			return Jwts.parser()
					.verifyWith((SecretKey)getKey())
					.build()
					.parseSignedClaims(token)
					.getPayload()
					.getSubject();
		}
		// Extract Expiration of token
	 public	Date extractExpiration (String token)
		{
			return Jwts.parser()
					.verifyWith((SecretKey) getKey())
					.build()
					.parseSignedClaims(token)
					.getPayload()
					.getExpiration();
		}
	 
	 // check expiration of token
	 
	 public boolean isExpired(String token)
	 {
		 return extractExpiration(token).before(new Date());
	 }
	
	 public boolean isValidToken(String token,String username)
	 {
		 String uname=extractUsername(token);
		 return uname.equals(username)&& !isExpired(token);
	 }
	}

	
