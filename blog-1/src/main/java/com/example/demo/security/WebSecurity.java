package com.example.demo.security;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;


@Configuration
@EnableWebSecurity
public class WebSecurity {
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginPage("/login")
				.defaultSuccessUrl("/blogContent")
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

public static InMemoryUserDetailsManager manager =  new InMemoryUserDetailsManager();

@Autowired
AccountRepository accountRepository;
@Bean
public UserDetailsService userDetailsService() {
		List<Account> accountList = accountRepository.findAll();
		for (Account account2 : accountList) {
			manager.createUser(User
					.withDefaultPasswordEncoder()
					.username(account2.getUsername())
					.password(account2.getPassword())
					.roles("USER")
					.build());
				
		}
	return manager;
	}
	
	
	

//	@Bean
//	public UserDetailsService userDetailsService() {
//		var manager = new InMemoryUserDetailsManager();
//		
//		manager.createUser(User
//				.withDefaultPasswordEncoder()
//				.username("Alice")
//				.password("ABC12345")
//				.roles("USER")
//				.build());
//		
//		return manager;
//	}
	
}