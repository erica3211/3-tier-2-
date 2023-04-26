package com.ds.groupware.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ds.groupware.service.UHService;

import javax.annotation.Resource;

@Controller 
public class HomeController {
	@Resource(name = "uhService")
	UHService uhservice;
	
	// 홈화면
	@GetMapping("/")
	public String index() {
		return "index";
	}
}