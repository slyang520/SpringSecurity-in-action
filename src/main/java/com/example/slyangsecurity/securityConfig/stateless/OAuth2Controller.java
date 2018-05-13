package com.example.slyangsecurity.securityConfig.stateless;

import com.alibaba.fastjson.JSONObject;
import com.example.slyangsecurity.securityConfig.RemoteRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OAuth2Controller {

	private Logger logger = LoggerFactory.getLogger(OAuth2Controller.class);

	@Autowired
	MyToken tokenManager;

	@GetMapping(value = "/login/oauth2/github")
	public String loginGitHub() {
		return "redirect:https://github.com/login/oauth/authorize?client_id=50ae751afa1282f037c6";
	}

	@GetMapping(value = "/login/github")
	public String callBack(@RequestParam String code) {

		//github.client.clientId=50ae751afa1282f037c6
		//github.client.clientSecret=8e675e556d7ebddbe3485ffeffcbabcd1b02d82a

		//https://github.com/login/oauth/access_token
		String url = "https://github.com/login/oauth/access_token";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", "50ae751afa1282f037c6");
		map.add("client_secret", "8e675e556d7ebddbe3485ffeffcbabcd1b02d82a");
		map.add("code", code);
		JSONObject jsonObject = RemoteRequestUtil.post(url, map, JSONObject.class);

		logger.info(jsonObject.toJSONString());

		//https://api.github.com/user
		String access_token = jsonObject.getString("access_token");
		String user_url = "https://api.github.com/user?access_token=" + access_token;
		JSONObject user_json = RemoteRequestUtil.getT(user_url, JSONObject.class);
		logger.info(user_json.toJSONString());

		String token = tokenManager.createToken();
		Oauth2Authentication authentication = new Oauth2Authentication("github_user_id",null);

		// token > user_id
		tokenManager.saveToken(token, authentication.getPrincipal());
		Map<String, Authentication> securityContextMap = tokenManager.getSecurityContextMap();
		// token >  Authentication
		securityContextMap.put(token, SecurityContextHolder.getContext().getAuthentication());

		return "redirect:http://localhost:3000/ilogin_stateless.html?ticket=" + token;
		
	}

}
