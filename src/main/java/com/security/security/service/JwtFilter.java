package com.security.security.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private jwtService jwtService;

    @Autowired
    private UserDetailsService Service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain)throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        // Check Authorization header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        // Validate token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
             {
            UserDetails userByUsername =Service.loadUserByUsername(username);
            
            if (jwtService.isValidToken(token, userByUsername.getUsername()))
            {
            	UsernamePasswordAuthenticationToken authToken =
            		    new UsernamePasswordAuthenticationToken(
            		        userByUsername,
            		        null,
            		        userByUsername.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}