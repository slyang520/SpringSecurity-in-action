package com.example.slyangsecurity.config.security;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;


public class TokenPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static final String SSO_CREDENTIALS = "N/A";

	public TokenPreAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.setAuthenticationManager(authenticationManager);
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return request.getHeader(Constance.SSO_API_TOKEN);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return SSO_CREDENTIALS;
	}

}
