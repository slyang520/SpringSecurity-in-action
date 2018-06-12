package com.example.slyangsecurity.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailHandler implements AuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(AuthFailHandler.class);

	static final String AUTH_FAIL_EXCEPTION = "__auth_fail_exception";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		logger.debug(" AuthFailHandler [onAuthenticationFailure]  {}  {}", exception.getMessage(), exception.getClass().getSimpleName());
		request.setAttribute(AUTH_FAIL_EXCEPTION, exception);
		//NOT write Response Here , Response can be write in NotLoginHandler.java
	}

}
