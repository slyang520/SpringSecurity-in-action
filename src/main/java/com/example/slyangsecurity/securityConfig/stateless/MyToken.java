package com.example.slyangsecurity.securityConfig.stateless;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@ConditionalOnProperty(value = "global.security.state", havingValue = "false")
@Component
public class MyToken implements ITokenManager {

	private Map<String, Authentication> securityContextMap = new ConcurrentHashMap<>();

	public Map<String, Authentication> getSecurityContextMap() {
		return securityContextMap;
	}

	/**
	 * mock redis
	 */
	private Map<String, Object> tokenMap = new ConcurrentHashMap<>();

	@Override
	public String createToken() {
		return UUID.randomUUID().toString();
	}

	@Override
	public boolean saveToken(String token, Object object) {
		return tokenMap.put(token, object) != null;
	}

	@Override
	public boolean delToken(String token) {
		return tokenMap.remove(token) != null;
	}

	@Override
	public Object getTokenValue(String token) {
		return tokenMap.get(token);
	}

	@Override
	public boolean validateToken(String token) {
		return tokenMap.containsKey(token);
	}

}
