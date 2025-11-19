package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerForm", new RegistrationForm());
		return "register.html";
	}
	
	@PostMapping("/register")
	public String registerUser(RegistrationForm form, Model model) {
		
		System.out.println("Email:" + form.getEmail());
		System.out.println("Password:" + form.getPassword());
		
		//TODO: Make sure first name is not empty
		if(form.getFirstName().isBlank()) {
			// send "First name cannot be empty"
			model.addAttribute("error", "First name cannot be empty");
			return "register.html";
		}
		
		
		//TODO: Make sure email is valid
		//TODO: Make sure password & confirm password are equal
		//TODO: Make sure password contains special character, number
		//TODO: Make sure email is unique
		//TODO: if any error exists; show it in the form
		//TODO: Create User entity object
		//TODO: Store the entity in db
		//TODO: Send successful message and redirect to login page.
	
		return "redirect:/";
	}
	
}
