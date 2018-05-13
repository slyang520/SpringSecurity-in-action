package com.example.slyangsecurity.securityConfig.stateless.mobile;

import com.example.slyangsecurity.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;


public class MobileAuthenticationProvider implements AuthenticationProvider {

	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;

		Map<String, Object> user = userService.findUserByMobile(mobileAuthenticationToken.getPrincipal());

		//UserDetailsImpl userDetails = buildUserDeatils(userVo);

		if (user == null) {
			throw new InternalAuthenticationServiceException("手机号不存在:" + mobileAuthenticationToken.getPrincipal());
		}

		MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(
				mobileAuthenticationToken.getPrincipal(),
				userService.buildAuthoritiesByMobile(mobileAuthenticationToken.getPrincipal()));

		authenticationToken.setDetails(userService.findUserByMobile(mobileAuthenticationToken.getPrincipal()));

		return authenticationToken;
	}


	@Override
	public boolean supports(Class<?> authentication) {
		return MobileAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
