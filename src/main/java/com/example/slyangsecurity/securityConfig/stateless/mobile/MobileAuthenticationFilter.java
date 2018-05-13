package com.example.slyangsecurity.securityConfig.stateless.mobile;


import com.example.slyangsecurity.securityConfig.stateless.Constance;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


	private static final String mobileParameter = "mobile";
	private boolean postOnly = true;

	public MobileAuthenticationFilter() {
		super(new AntPathRequestMatcher(Constance.LOGIN_MOBILE, "POST"));
	}

	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) throws AuthenticationException {

		if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String mobile = obtainMobile(request);
		if (mobile == null) {
			mobile = "";
		}
		mobile = mobile.trim();

		MobileAuthenticationToken mobileAuthenticationToken = new MobileAuthenticationToken(mobile);
		setDetails(request, mobileAuthenticationToken);

		return this.getAuthenticationManager().authenticate(mobileAuthenticationToken);
	}

	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	protected void setDetails(HttpServletRequest request,
							  MobileAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

}
