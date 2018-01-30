package com.example.slyangsecurity;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	/**
	 * URL 权限验证部分
	 */

	@GetMapping(value = "/test1")
	String testAnonymous() {
		return "testAnonymous";
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/test2")
	String testRoleUser() {
		return "testRoleUser";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/test3")
	String testRoleAdmin() {
		return "testRoleAdmin";
	}

	@GetMapping(value = "/test4")
	String testRoleAdmin2() {
		return "testRoleAdmin2";
	}

	@PreAuthorize("hasAuthority('user:select')")
	@GetMapping(value = "/test5")
	String testUserAuthority() {
		return "testUserAuthoritySelect";
	}

	@PreAuthorize("hasAuthority('user:add')")
	@GetMapping(value = "/test6")
	String testUserAuthority2() {
		return "testUserAuthorityADD";
	}

	@GetMapping(value = "/admin/test")
	String testUserAuthorityAdmin() {
		return "testUserAuthorityAdmin";
	}


}
