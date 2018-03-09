package com.goshine.core.validate.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.goshine.core.validate.code.sms.SmsCodeSender;

@RestController
public class ValidateCodeController {

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	
	public static final String SESSION_SMS_KEY = "SESSION_KEY_SMS_CODE";

	@Autowired
	private ValidateCodeGenerator imageCodeGenerator;
	
	@Autowired
	private ValidateCodeGenerator smsCodeGenerator;
	
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImageCode image = (ImageCode) imageCodeGenerator.createImageCode(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, image);
		ImageIO.write(image.getImage(), "JPEG", response.getOutputStream());
	}
	
	@GetMapping("/code/sms")
	public void createSms(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
		ValidateCode smsCode = smsCodeGenerator.createImageCode(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_SMS_KEY, smsCode);
		String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
		smsCodeSender.send(mobile, smsCode.getCode());
	} 
}
