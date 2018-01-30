package com.example.slyangsecurity.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPasswordEncoder {

	public static void main(String[] args) {
		String rawPassword = "123456";
		PasswordEncoder passwordEncoder =
				new BCryptPasswordEncoder();

		String encodedPassword = passwordEncoder.encode(rawPassword);
		boolean flag = passwordEncoder.matches(rawPassword, encodedPassword);

		System.out.println(encodedPassword + "        " + flag);
	}

}
