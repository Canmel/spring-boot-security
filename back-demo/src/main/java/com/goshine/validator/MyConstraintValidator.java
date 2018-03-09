package com.goshine.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.goshine.service.HelloService;

public final class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {
	
	@Autowired
	private HelloService HelloService;
	
	@Override
	public void initialize(MyConstraint arg0) {
		System.out.println("my validatior init");
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		HelloService.greeting("tom");
		System.out.println(arg0);
		return false;
	}


}
