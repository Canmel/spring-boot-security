package com.goshine.browser;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 根据用户名查找用户信息
		System.out.println("根据用户名查找用户信息");
		logger.info("根据用户名查找用户信息");
		// return new User(username, "123456",
		// AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		String pe = passwordEncoder.encode("123456");
		// $2a$10$P2E9hpG1Tq1N8xPq8743iOwSK.kbJxkGUHu2ESUTCaAQp2Db7vqCS
		System.out.println(pe);
		if(username == null || StringUtils.isBlank(username)) {
			throw new UsernameNotFoundException("用户名密码不正确");
		}
		return new User(username, pe, true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
