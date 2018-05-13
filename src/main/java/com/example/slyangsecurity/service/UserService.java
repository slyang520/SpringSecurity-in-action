package com.example.slyangsecurity.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

public interface UserService {

	Map<String, Object> findUserByMobile(String mobile);

	Map<String, Object> findUserByUserName(String userName);

	Collection<? extends GrantedAuthority> buildAuthoritiesByMobile(String mobile);

	Collection<? extends GrantedAuthority> buildAuthoritiesByUserName(String userName);

}
