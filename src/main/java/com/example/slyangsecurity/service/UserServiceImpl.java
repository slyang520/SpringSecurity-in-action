package com.example.slyangsecurity.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

	// todo
	@Override
	public Map<String, Object> findUserByMobile(String mobile) {

		Map<String, Object> userINfo = new HashMap<>();
		userINfo.put("mobile", mobile);

		return userINfo;
	}

	@Override
	public Map<String, Object> findUserByUserName(String mobile) {
		return null;
	}

	// todo
	@Override
	public Collection<? extends GrantedAuthority> buildAuthoritiesByMobile(String mobile) {

		HashSet hashSet = new HashSet<>(Arrays.asList(
				new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_USER"),
				new SimpleGrantedAuthority("user:select"),
				new SimpleGrantedAuthority("user:add"),
				new SimpleGrantedAuthority("user:del"),
				new SimpleGrantedAuthority("user:update")));
		return hashSet;
	}

	@Override
	public Collection<? extends GrantedAuthority> buildAuthoritiesByUserName(String userName) {

		HashSet hashSet = new HashSet<>(Arrays.asList(
				new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_USER"),
				new SimpleGrantedAuthority("user:select"),
				new SimpleGrantedAuthority("user:add"),
				new SimpleGrantedAuthority("user:del"),
				new SimpleGrantedAuthority("user:update")));
		return hashSet;
	}

}