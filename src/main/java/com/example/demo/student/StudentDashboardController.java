package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Routes;
import com.example.demo.Templates;
import com.example.demo.auth.Authentication;
import com.example.demo.user.User;
import com.example.demo.user.UserType;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentDashboardController {

	@Autowired
	private Authentication authentication;

	@GetMapping(Routes.STUDENT_DASHBOARD)
	public String getDashboard(HttpServletRequest request, Model model) {
		// DONE: get user from cookie
		// user verification
		// user identification
		User user = authentication.authenticate(request);

		if (user == null || user.getType() != UserType.STUDENT) {
			return "redirect:" + Routes.LOGIN;
		}
		
		model.addAttribute("user", user);
		
		return Templates.STUDENT_DASHBOARD;
	}

}
