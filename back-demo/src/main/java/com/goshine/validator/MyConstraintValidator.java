package com.goshine.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public final class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {
	
	@Override
	public void initialize(MyConstraint arg0) {
		System.out.println("my validatior init");
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		System.out.println(arg0);
		return false;
	}


}
