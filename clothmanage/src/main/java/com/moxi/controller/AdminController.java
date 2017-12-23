package com.moxi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("index")
	public ModelAndView page1() {
		return new ModelAndView("/index.html");
	}

	@RequestMapping("map")
	public Map<String, String> map1() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "老张");
		map.put("age", "24");
		map.put("sex", "男");
		map.put("feature", "青春痘很多");
		map.put("dream", "没梦想啊");
		return map;
	}
}