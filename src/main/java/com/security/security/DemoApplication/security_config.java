package com.security.security.DemoApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.WebAuthnConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.security.service.JwtFilter;

@Configuration
public class security_config {
	
	@Autowired
	JwtFilter filter;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user=   
//				User.withUsername("user") // user is utility class 
//				.password("$2a$10$oWf2XPn.2//3BWr2.rNwcuERYZpfrlJZFkfogyNBX91sNTE2jfpE6")//no authorize object permission 
//				.roles("USER")
//				.build();
//		
//		UserDetails admin=
//				User.withUsername("admin")
//				.password("$2a$10$I22u4AZ3tk/xlAgrvW0oRed7mb4cWo5F4GG/EoP4uFW8sT0Xx9q1G")// length is 32 bits
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user,admin);
//		
////	}
//	@Bean
//	public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(auth->
//		auth.requestMatchers("/user").hasAnyRole("ADMIN","USER")
//		.requestMatchers("/admin").hasRole("ADMIN")
//		.permitAll()
//		.anyRequest()
//		.authenticated())
//		.httpBasic(Customizer.withDefaults());
//		return http.build();
//		
//	}
//
//}
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception  {
		httpSecurity
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth
				.requestMatchers("/save","/login").permitAll()
				.requestMatchers("/student").hasAnyRole("TRAINER","STUDENT")
				.requestMatchers("/trainer").hasAnyRole("TRAINER")
				.anyRequest().authenticated());
//		.httpBasic(Customizer.withDefaults());return httpSecurity.build();
		
		httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	return httpSecurity.build();
	}
	
	
	
//@Bean
//public AuthenticationManager authenticationManager(
//        AuthenticationConfiguration configuration) throws Exception {
//    return configuration.getAuthenticationManager();
//}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();
	}
}