package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	private void createAdminUserIfNotExist() {
		if (userRepository.count() == 0) {
			User admin = new User();
			admin.setFirstName("Admin");
			admin.setEmail("admin@alphaschool.com");
			admin.setUsername("admin@alphaschool.com");
			admin.setPassword(passwordEncoder.encode("admin@123"));
			admin.setGender(Gender.MALE);
			admin.setType(UserType.ADMIN);
			userRepository.save(admin);
		}
	}

}
