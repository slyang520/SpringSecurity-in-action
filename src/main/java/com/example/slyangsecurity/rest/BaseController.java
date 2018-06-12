package com.example.slyangsecurity.rest;

import com.example.slyangsecurity.common.exception.GlobalException;
import com.example.slyangsecurity.common.utils.GlobalCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public abstract class BaseController {

	protected Object getUser() throws GlobalException {
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication instanceof PreAuthenticatedAuthenticationToken) {
			return authentication.getDetails();
		} else {
			throw new GlobalException(GlobalCode.USER_NOT_LOGIN);
		}
	}

}
