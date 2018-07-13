package com.example.slyangsecurity.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ftl")
public class FtlController {

	private String getTheme(String url) {
		return "theme_ftl/" + url;
	}

	@RequestMapping("")
	public String index() {
		return getTheme("index");
	}

	@RequestMapping("/hello")
	public String test() {
		return getTheme("test_ftl");
	}


}
