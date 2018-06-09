package com.example.slyangsecurity.config.security;

import com.example.slyangsecurity.common.utils.GlobalCode;
import com.example.slyangsecurity.common.utils.R;
import com.example.slyangsecurity.common.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * AuthenticationEntryPoint
 * 是在用户没有登录时用于引导用户进行登录认证的
 */
public class NotLoginHandler implements AuthenticationEntryPoint {

	private Logger logger = LoggerFactory.getLogger(NotLoginHandler.class);

	@Override
	public void commence(HttpServletRequest request,
						 HttpServletResponse response,
						 AuthenticationException authException) throws IOException, ServletException {
		logger.debug(" NotLoginHandler [commence]  {}  {}", authException.getMessage(), authException.getClass().getSimpleName());

		if (authException instanceof InsufficientAuthenticationException
				|| authException instanceof CredentialsExpiredException
				) {
			WebUtil.writeJSON(R.errorGlobal(GlobalCode.TOKEN_INVALID), response);
		} else {
			WebUtil.writeJSON(R.errorGlobal(GlobalCode.USER_NOT_LOGIN), response);
		}

	}
}
