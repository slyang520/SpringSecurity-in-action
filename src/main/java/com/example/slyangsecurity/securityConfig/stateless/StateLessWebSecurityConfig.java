package com.example.slyangsecurity.securityConfig.stateless;


import com.example.slyangsecurity.securityConfig.UserDetailsServiceMockJDBC;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import javax.annotation.PostConstruct;


@ConditionalOnProperty(value = "global.security.state", havingValue = "false")
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
		logger.error("*********     ***************");
		logger.error("WebSecurity  stateless  start");
		logger.error("*********     ***************");
	}

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
				.antMatchers("/test1", "/", "/ilogin_stateless.html").permitAll()
				.antMatchers("/admin/**", "/test4").hasRole("ADMIN")
				.anyRequest().authenticated();

		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf()
				.disable();

		//injection security context
		http.addFilterAfter(iSecurityContextPersistenceFilter,
				SecurityContextPersistenceFilter.class);

		http.formLogin()
				.loginPage("/ilogin_stateless.html")
				.loginProcessingUrl("/ilogin_ajax")
				.usernameParameter("name")
				.passwordParameter("password")
				.successHandler(loginSuccessHandlerStateLess)
				.failureHandler(new LoginFailureHandlerStaeLess());

		http.logout()
				.logoutSuccessUrl("/");
	}

	@Autowired
	UserDetailsServiceMockJDBC userDetailsServiceMockJDBC;

	@Autowired
	LoginSuccessHandlerStateLess loginSuccessHandlerStateLess;

	@Autowired
	ISecurityContextPersistenceFilter iSecurityContextPersistenceFilter;

	/**
	 * 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsServiceMockJDBC);
		auth.authenticationProvider(provider);
		//PreAuthenticatedAuthenticationProvider provider2 =
		//new PreAuthenticatedAuthenticationProvider();
		//auth.authenticationProvider(provider2);

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
