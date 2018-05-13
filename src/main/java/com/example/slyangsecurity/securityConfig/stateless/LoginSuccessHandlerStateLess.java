package com.example.slyangsecurity.securityConfig.stateless;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSuccessHandlerStateLess implements AuthenticationSuccessHandler {

	@Autowired
	MyToken tokenManager;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String token = tokenManager.createToken();

		tokenManager.saveToken(token, authentication.getPrincipal());

		Map<String, Object> map = new HashMap<>();

		Map<String, Authentication> securityContextMap = tokenManager.getSecurityContextMap();

		securityContextMap.put(token, SecurityContextHolder.getContext().getAuthentication());

		map.put("token", token);
		map.put("user", authentication.getPrincipal());

		Writer writer = response.getWriter();
		writer.write(JSONObject.toJSONString(map));
	}

}
