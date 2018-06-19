package com.example.slyangsecurity.config.security;

import com.example.slyangsecurity.common.exception.GlobalCode;
import com.example.slyangsecurity.common.utils.R;
import com.example.slyangsecurity.common.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AccessDeniedHandler用于在用户已经登录了，
 * 但是访问了其自身没有权限的资源时做出对应的处理。
 * <p>
 * ( ^^需要特殊处理^^ )
 * PreAuthorize和PostAuthorize进行访问控制
 * 由于没有抛出到外层，
 * 你自定义的AccessDeniedHandler也就不能处理了
 */
public class NotAuthorityHandler implements AccessDeniedHandler {

	private Logger logger = LoggerFactory.getLogger(NotLoginHandler.class);

	@Override
	public void handle(HttpServletRequest request,
					   HttpServletResponse response,
					   AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.debug(" NotLoginHandler [commence]  {}  {}", accessDeniedException.getMessage(), accessDeniedException.getClass().getSimpleName());
		WebUtil.writeJSON(R.errorGlobal(GlobalCode.NOT_AUTHORITY), response);
	}
}
