package com.example.slyangsecurity.securityConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDetailsServiceMockJDBC implements UserDetailsService {

	private static final Log logger = LogFactory.getLog(UserDetailsServiceMockJDBC.class);

	private static final Map<String, String> userMap = new HashMap<>();
	private static final Map<String, Set<SimpleGrantedAuthority>> userRoleAuthority = new HashMap<>();

	
	/**
	 * MockDB
	 *
	 * userMap > username, password
	 *
	 * userRoleAuthority > username,[role,authority]
	 */
	static {
		userMap.put("user", "user");
		userMap.put("root", "root");
		userMap.put("admin", "admin");
		userMap.put("github_user_id", "");

		userRoleAuthority.put("user", new HashSet<>(Arrays.asList(
				new SimpleGrantedAuthority("ROLE_USER"),
				new SimpleGrantedAuthority("user:select"))));
		userRoleAuthority.put("root", new HashSet<>(Arrays.asList(
				new SimpleGrantedAuthority("ROLE_ROOT"),
				new SimpleGrantedAuthority("user:select"))));
		userRoleAuthority.put("admin", new HashSet<>(Arrays.asList(
				new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_USER"),
				new SimpleGrantedAuthority("user:select"),
				new SimpleGrantedAuthority("user:add"),
				new SimpleGrantedAuthority("user:del"),
				new SimpleGrantedAuthority("user:update"))));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password = userMap.get(username);
		if (password == null) {
			logger.debug("用户不存在用户不存在用户不存在用户不存在用户不存在");
			throw new UsernameNotFoundException("用户不存在");
		}
		logger.debug("用户存在用户存在用户存在");
		Set<SimpleGrantedAuthority> authorities = userRoleAuthority.get(username);
		return new User(username, password, authorities);
	}

}
