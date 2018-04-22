package com.goshine.web.controller;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.goshine.core.base.R;
import com.goshine.service.BaseService;
import com.goshine.service.UserService;
import com.goshine.web.enums.UserStatus;
import dto.PageQuery;
import dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import exceptions.UserNotExistException;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

	@Autowired
	public UserService userService;

	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}

	@GetMapping("/current")
	public User getUserDetail(@AuthenticationPrincipal UserDetails user) {
		return userService.loadUserByUserName(user.getUsername());
	}
	
	@GetMapping
	@ResponseBody
	public R query(User user) {
		PageInfo pageInfo = super.query(user);
		return R.ok().page(pageInfo);
	}

	@GetMapping("/{id:\\d+}")
	public User getInfo(@PathVariable(name = "id") String id) {
		User user = new User();
		user.setUsername("tom");
		System.out.println("调用了geiInfo服务");
		return user;
	}

	@PostMapping
	@ResponseBody
	public R create(@Valid User user, BindingResult errors) {
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		user.setId(1l);
		return R.ok().put("msg", "保存用户成功");
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

	@Override
	public BaseService getService() {
		return userService;
	}
}
