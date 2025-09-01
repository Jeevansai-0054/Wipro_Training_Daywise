package com.wipro.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@GetMapping("/greet")
	public String greet()
	{
		return "Hello From Greet";
	}
	@GetMapping("/hello")
	public String greet1()
	{
		return "Hello ! Good Morning";
	}
}
