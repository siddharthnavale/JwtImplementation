package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.JwtRequest;
import com.example.demo.Entity.JwtResponse;
import com.example.demo.jwtUtil.JwtUtil;
import com.example.demo.services.CustomerUserDetailsService;

@RestController
public class JWTController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		System.out.println(jwtRequest);
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		}
		 catch(UsernameNotFoundException e){
			 
			 e.printStackTrace();
			 throw new Exception("Bad Credential");
		 }
		
		UserDetails userDetails = this.customerUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
		String token=this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
		
	}
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

