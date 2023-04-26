package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/welcome")
	public String welcome() {
		
		String text="This is private page This Page is not allowed to unauthenticated "
				+ "users";
		
		return text;
		
	}
	
}
