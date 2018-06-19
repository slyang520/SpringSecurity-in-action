package com.example.slyangsecurity.config.security;

import com.example.slyangsecurity.common.exception.GlobalCode;
import com.example.slyangsecurity.common.utils.R;
import com.example.slyangsecurity.common.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		Exception exception = (Exception) request.getAttribute(AuthFailHandler.AUTH_FAIL_EXCEPTION);
		if (exception != null) {
			//todo case 凭证失效，账号锁定...
			WebUtil.writeJSON(R.errorGlobal(GlobalCode.AUTH_NOT_SUCCESS), response);
		} else {
			WebUtil.writeJSON(R.errorGlobal(GlobalCode.USER_NOT_LOGIN), response);
		}
	}

}
