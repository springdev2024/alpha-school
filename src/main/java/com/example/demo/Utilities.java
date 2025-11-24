package com.example.demo;

public class Utilities {
	
	/**
	 * checks whether the provided password is valid satisfying the required constraints
	 * at least 8 characters
	 * at least 1 numeric
	 * at least 1 uppercase letter
	 * @param password
	 * @return true if password is valid
	 */
	public static boolean isValidPassword(String password) {
		return password.length() >= 8
		&& password.matches(".*[0-9].*")
		&& password.matches(".*[A-Z].*");
	}
	
}
