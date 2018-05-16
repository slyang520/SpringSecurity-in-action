package com.example.slyangsecurity.rest;


import com.example.slyangsecurity.common.utils.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	/**
	 * URL 权限验证部分
	 */
	@GetMapping(value = "/test1")
	public R testAnonymous() {
		return R.ok("testAnonymous");
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/test2")
	public R testRoleUser() {
		return R.ok("testRoleUser");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/test3")
	public R testRoleAdmin() {
		return R.ok("testRoleAdmin");
	}

	@GetMapping(value = "/test4")
	public R testRoleAdmin2() {
		return R.ok("testRoleAdmin2");
	}

	@PreAuthorize("hasAuthority('user:select')")
	@GetMapping(value = "/test5")
	public R testUserAuthority() {
		return R.ok("testUserAuthoritySelect");
	}

	@PreAuthorize("hasAuthority('user:add')")
	@GetMapping(value = "/test6")
	public R testUserAuthority2() {
		return R.ok("testUserAuthorityADD");
	}

	@GetMapping(value = "/admin/test")
	public R testUserAuthorityAdmin() {
		return R.ok("testUserAuthorityAdmin");
	}

}
