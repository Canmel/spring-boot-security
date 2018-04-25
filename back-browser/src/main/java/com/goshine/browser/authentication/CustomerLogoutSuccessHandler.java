package com.goshine.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goshine.core.properties.LoginType;
import com.goshine.core.properties.SecurityProperties;
import com.goshine.core.utils.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customerLogoutSuccessHandler")
public class CustomerLogoutSuccessHandler implements LogoutSuccessHandler {

	private String signOutUrl;

	private ObjectMapper objectMapper = new ObjectMapper();

	public CustomerLogoutSuccessHandler() {
	}

	public CustomerLogoutSuccessHandler(String signOutUrl) {
		this.signOutUrl = signOutUrl;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		if(StringUtils.isBlank(signOutUrl)){
			httpServletResponse.setContentType("application/json;charset=UTF-8");
			httpServletResponse.getWriter().write(objectMapper.writeValueAsString(ResultUtil.success(200,"退出成功")));
		}else{
			httpServletResponse.sendRedirect(signOutUrl);
		}

	}

}
