package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.example.demo.security.*;
import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;

@Service
public class AccountService2 {
	@Autowired
	AccountRepository repository2;

//判断账户是否存在
	public boolean validateAccount(String username, String password) {
		Account account = repository2.findByUsername(username);
		if (account == null || !account.getPassword().equals(password)) {
			return false;
		} else {
			return true;
		}
	}

//添加账户
	public boolean createAccount(String username, String password, String confirmPassword) {
		if (repository2.findByUsername(username) == null && password.equals(confirmPassword)) {
			WebSecurity.manager.createUser(User
					.withDefaultPasswordEncoder()
					.username(username)
					.password(password)
					.roles("USER")
					.build());
			repository2.save(new Account(username, password));
			return true;
		} else {
			return false;
		}
	}

}
