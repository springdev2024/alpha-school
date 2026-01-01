package com.example.demo.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Routes;
import com.example.demo.Templates;
import com.example.demo.auth.Authentication;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserType;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminDashboardController {

	@Autowired
	private Authentication authentication;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(Routes.ADMIN_DASHBOARD)
	public String getDashboard(HttpServletRequest request, Model model) {
		// DONE: get user from cookie
		// user verification
		// user identification
		User user = authentication.authenticate(request);

		if (user == null || user.getType() != UserType.ADMIN) {
			return "redirect:" + Routes.LOGIN;
		}

		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("user", user);

		return Templates.ADMIN_DASHBOARD;
	}

}
