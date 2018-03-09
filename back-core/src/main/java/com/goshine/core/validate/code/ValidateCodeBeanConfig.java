package com.goshine.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goshine.core.properties.SecurityProperties;
import com.goshine.core.validate.code.sms.DefaultSmsCodeSender;
import com.goshine.core.validate.code.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties; 
	
	@Bean
	@ConditionalOnMissingBean(name="imageCodeGenerator")
	public ValidateCodeGenerator imageCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return new DefaultSmsCodeSender();
	}
}
