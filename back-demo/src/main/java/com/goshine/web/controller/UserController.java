package com.goshine.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import dto.User;
import exceptions.UserNotExistException;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}
	
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	public List<User> query(@RequestParam(required = false) String username,
			@PageableDefault(page = 1, size = 10, sort = "id,asc") Pageable pageable) {
		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable(name = "id") String id) {
		User user = new User();
		user.setUsername("tom");
		System.out.println("调用了geiInfo服务");
		return user;
	}

	@PostMapping
	public User create(@Valid @RequestBody User user, BindingResult errors) {
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		user.setId("1"); 
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	public User edit(@Valid @RequestBody User user, BindingResult errors) {
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError) error;
				
				String message = fieldError.getField() + error.getDefaultMessage();
				System.out.println(message);
			}
			);
		}
		return user;
	}
	
	
	@DeleteMapping("/{id:\\d+}") 
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}
	
	@GetMapping("/error/{id:\\d+}") 
	public void toError(@PathVariable String id) {
		throw new UserNotExistException(id);
	}
}
