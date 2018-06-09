package com.example.slyangsecurity.config.security;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import javax.annotation.PostConstruct;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class StateLessWebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Log logger = LogFactory.getLog(StateLessWebSecurityConfig.class);

	@PostConstruct
	public void PostConstruct() {
		logger.info("*********     ***************");
		logger.info("WebSecurity  stateless  start");
		logger.info("*********     ***************");
	}

	@Bean
	TokenPreAuthenticationFilter tokenPreAuthenticationFilter() throws Exception {
		return new TokenPreAuthenticationFilter(this.authenticationManager());
	}

	@Bean
	TokenAuthUserService tokenAuthUserService() {
		return new TokenAuthUserService();
	}

	@Bean
	NotLoginHandler notLoginHandler() {
		return new NotLoginHandler();
	}

	@Bean
	NotAuthorityHandler notAuthorityHandler() {
		return new NotAuthorityHandler();
	}

//	@Bean
//	DecodeApiFilter decodeApiFilter() {
//		return new DecodeApiFilter();
//	}

	/**
	 * prePostEnabled :决定Spring Security的前注解是否可用 [@PreAuthorize,@PostAuthorize,..]
	 * secureEnabled : 决定是否Spring Security的保障注解 [@Secured] 是否可用
	 * jsr250Enabled ：决定 JSR-250 annotations 注解[@RolesAllowed..] 是否可用.
	 */

	/**
	 * @ PreAuthorize 注解适合进入方法前的权限验证， @PreAuthorize可以将登录用户的roles/permissions参数传到方法中。
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/js/**", "/css/**", "/**/*.ico").permitAll()

				.antMatchers(
						"/",
						"/test/cookies",
						"/test1",
						"/ilogin_stateless.html",
						"/login/github",
						"/login/oauth2/github",
						"/oauth/**/callback").permitAll()

				.antMatchers("/admin/**", "/test4").hasRole("ADMIN")
				.anyRequest().authenticated();

		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf()
				.disable();

		http.addFilterBefore(tokenPreAuthenticationFilter(), AnonymousAuthenticationFilter.class);

		http.exceptionHandling()
				.authenticationEntryPoint(notLoginHandler())
				.accessDeniedHandler(notAuthorityHandler());

	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider =
				new PreAuthenticatedAuthenticationProvider();
		preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(tokenAuthUserService());
		auth.authenticationProvider(preAuthenticatedAuthenticationProvider);

	}

	/**
	 * Web层面的配置，
	 * 一般用来配置无需安全检查的路径
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true);
	}

}
