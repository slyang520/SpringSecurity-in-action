package com.example.slyangsecurity.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String test(Model model) {

		model.addAttribute("url", "http://freemarker.org/");
		model.addAttribute("name", "slyang");
		model.addAttribute("message", "Welcome to use Freemarker");

		//对象
		Map<String, Object> objectMap = new HashMap<>();
		objectMap.put("name", "slyang");
		objectMap.put("address", "wuhan");
		objectMap.put("age", "25");
		objectMap.put("height", "175cn");

		model.addAttribute("my_obj", objectMap);

		// 列表
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("user_name", "slyang01");
		map1.put("user_tel", "110");

		Map<String, Object> map2 = new HashMap<>();
		map2.put("user_name", "slyang02");
		map2.put("user_tel", "112");

		Map<String, Object> map3 = new HashMap<>();
		map3.put("user_name", "slyang03");
		map3.put("user_tel", "113");

		list.add(map1);
		list.add(map2);
		list.add(map3);

		model.addAttribute("my_list", list);


		return getTheme("test_ftl");
	}


}
