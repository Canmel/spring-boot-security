/**
 * 
 */
package com.goshine.core.validate.code.sms;

/**
 * @author baily
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.goshine.core.validate.code.sms.SmsCodeSender#send(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机： " + mobile + " 发送验证码 : " + code);
	}

}
