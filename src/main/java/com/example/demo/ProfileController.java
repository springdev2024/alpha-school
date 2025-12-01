package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
	
	@Autowired
	private Authentication authentication;
	
	@GetMapping("/profile")
	public String getProfilePage(HttpServletRequest request, Model model) {
		//DONE: get user from cookie
		// user verification
		// user identification
		User user = authentication.authenticate(request);
		
		if(user == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("user", user);
		return "profile.html";
	}
	
}
