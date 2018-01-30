package com.example.slyangsecurity.securityConfig.stateless;

public interface ITokenManager {

	String createToken();

	boolean saveToken(String token, Object object);

	boolean delToken(String token);

	Object getTokenValue(String token);

	boolean validateToken(String token);

}
