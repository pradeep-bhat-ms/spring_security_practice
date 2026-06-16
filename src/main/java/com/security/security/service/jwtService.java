package com.security.security.service;

import org.springframework.stereotype.Service;

@Service
public class jwtService {

	public static String generateToken() {
		
		return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6ImFkbWluIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.Yd1ZZ7X56a1rX4Wdhcwen8Z1ZS_KIiyScEvRDRI9zyI";
	}

}
