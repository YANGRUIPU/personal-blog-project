package com.example.demo.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginPage("/login")
				.defaultSuccessUrl("/blogManagementSystem")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/user-login")
				.failureUrl("/login?error")
				.permitAll()
		).authorizeHttpRequests(authz -> authz
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
				.permitAll()
				.requestMatchers("/register", "/login","*.css","*.js")
				.permitAll()
				.anyRequest()
				.authenticated()
		);
		
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		var manager = new InMemoryUserDetailsManager();
		
		manager.createUser(User
				.withDefaultPasswordEncoder()
				.username("Alice")
				.password("ABC12345")
				.roles("USER")
				.build());
		
		return manager;
	}
	
}