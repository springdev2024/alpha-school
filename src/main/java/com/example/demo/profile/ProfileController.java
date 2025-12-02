package com.example.demo.profile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.auth.Authentication;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

	@Autowired
	private Authentication authentication;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/profile")
	public String getProfilePage(HttpServletRequest request, Model model) {
		// DONE: get user from cookie
		// user verification
		// user identification
		User user = authentication.authenticate(request);

		if (user == null) {
			return "redirect:/login";
		}

		model.addAttribute("user", user);
		return "profile.html";
	}

	@GetMapping("/profile/edit")
	public String getProfileEditPage(HttpServletRequest request, Model model) {
		// TODO: show profile edit
		User user = authentication.authenticate(request);
		if (user == null) {
			// TODO: show Not Authorized message
			return "redirect:/login";
		}

		model.addAttribute("user", user);
		return "profile-edit.html";
	}

	@PostMapping("/profile/edit")
	public String updateProfile(ProfileEditForm form, HttpServletRequest request) throws IOException {
		// TODO: show profile edit
		User user = authentication.authenticate(request);
		if (user == null) {
			// TODO: show Not Authorized message
			return "redirect:/login";
		}
		
		System.out.println("Address: " + form.getAddress());
		System.out.println("Image: " + form.getProfile().getOriginalFilename());

		//DONE: update user's address
		user.setAddress(form.getAddress());
		
		//DONE: save user's profile picture
		MultipartFile profile = form.getProfile();
		if(profile != null && !profile.isEmpty()) {
			
			//TODO: max file size limit
			//TODO: extract file extension from original filename
			String originalFileName = profile.getOriginalFilename();
			System.out.println("Original Filename: " + originalFileName);
			
			String fileExtension = "jpg";
			String fileName = Instant.now().toEpochMilli() + "." + fileExtension;
					
			Path path = Paths.get("uploads", fileName);
			
			Files.copy(profile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			
			Files.delete(Paths.get("uploads", user.getProfilePicture()));
			
			user.setProfilePicture(fileName);
		}
		
		
		userRepository.save(user);
		
		return "redirect:/profile";
	}

}
