package com.goshine.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = 6129001176250807048L;
	
	public ValidateCodeException(String msg) {
		super(msg);
	}

}
