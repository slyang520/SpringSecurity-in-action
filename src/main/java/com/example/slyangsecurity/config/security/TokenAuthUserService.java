package com.example.slyangsecurity.config.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;

import java.util.*;

public class TokenAuthUserService
		implements
		AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

	private static final Log logger = LogFactory.getLog(StateLessWebSecurityConfig.class);

	@Override
	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {

		String headerToken = (String) token.getPrincipal();

		if (StringUtils.isEmpty(headerToken)) {
			throw new UsernameNotFoundException("USER NOT FIND");
		}

		//Mock
		Collection<? extends GrantedAuthority> authorities = new HashSet<>(Arrays.asList(
				new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_USER"),
				new SimpleGrantedAuthority("user:select"),
				new SimpleGrantedAuthority("user:add"),
				new SimpleGrantedAuthority("user:del"),
				new SimpleGrantedAuthority("user:update")));


		//CASE:   TOKEN 过期
		//User user = new User(headerToken, headerToken, true, true, false, true, authorities);
		User user = new User(headerToken, headerToken, true, true, true, true, authorities);

		if (logger.isDebugEnabled()) {
			logger.debug("验证身份通过：{}> " + headerToken);
		}

		// to set userDetails
		Map<String,Object> test = new HashMap();
		test.put("test","ff");
		test.put("test1","1ff");
		test.put("test2","2ff");
		token.setDetails(test);

		return user;
	}

}
