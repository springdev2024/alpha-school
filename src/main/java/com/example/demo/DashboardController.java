package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

	@GetMapping("/dashboard")
	public String getDashboard(HttpServletRequest request) {
		Authentication.authenticate(request);
		
		//TODO: get user from cookie
		// user verification
		// user identification
		
		
		return "dashboard.html";
	}
	
}
