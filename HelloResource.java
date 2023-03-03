package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.Repository.RoleRepository;
import com.example.security.Repository.UserRepository;
import com.example.security.Util.JwtUtil;
import com.example.security.config.services.UserService;
import com.example.security.models.AUthenticationResponse;
import com.example.security.models.AuthenticationRequest;
import com.example.security.models.Role;
import com.example.security.models.User;

@RestController
public class HelloResource {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}

	@GetMapping(value = "/admin")
	public String adminHome() {
		return "admin";
	}

	@PostMapping(value = "addRole")
	public Role addRole(@RequestBody Role role) {
		return roleRepository.save(role);

	}

	@PostMapping(value = "addUser")
	public User addRole(@RequestBody User user) {
		return userService.addUser(user);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthentivationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			e.printStackTrace();
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AUthenticationResponse(jwt));

	}
}
