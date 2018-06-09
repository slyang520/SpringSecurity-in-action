package com.example.slyangsecurity.rest;

import com.example.slyangsecurity.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Servlet;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
public class LoginController {

//	@PostMapping(value = "/login")
//	public R login() {
//		return R.ok();
//	}

//	@GetMapping(value = "/test/cookies")
//	public R test(HttpServletResponse response) {
//
//		Cookie cookieToken = new Cookie("_token_", UUID.randomUUID().toString());
//		cookieToken.setPath("/");
//		cookieToken.setHttpOnly(true);
//		cookieToken.setMaxAge(60*60*24*30);
//
//		Cookie cookieUserName = new Cookie("_username_", "slyang23");
//		cookieUserName.setPath("/");
//		cookieUserName.setHttpOnly(true);
//		cookieUserName.setMaxAge(60*60*24);
//
//		response.addCookie(cookieToken);
//		response.addCookie(cookieUserName);
//
//		return R.ok();
//	}
}
