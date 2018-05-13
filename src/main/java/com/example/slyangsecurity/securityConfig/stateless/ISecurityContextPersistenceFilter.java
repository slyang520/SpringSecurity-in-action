package com.example.slyangsecurity.securityConfig.stateless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 从请求头获取认真信息
 */
@Component
public class ISecurityContextPersistenceFilter extends OncePerRequestFilter {

	@Autowired
	MyToken myToken;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String HEAD = request.getHeader(Constance.API_TOKEN);

		if (!StringUtils.isEmpty(HEAD)) {
			SecurityContext securityContext = SecurityContextHolder.getContext();

			Authentication authentication = myToken.getSecurityContextMap().get(HEAD);

			if (authentication != null) {
				securityContext.setAuthentication(authentication);
			}

		}
		filterChain.doFilter(request, response);

	}

}
