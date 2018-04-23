package com.goshine.service.serviceImpl;

import com.goshine.core.base.DemoBaseMapper;
import com.goshine.mapper.UserMapper;
import com.goshine.service.UserService;
import dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public DemoBaseMapper getMapper() {
        return userMapper;
    }

    @Override
    public User loadUserByUserName(String userName) {
        User user = new User();
        user.setUsername(userName);
        return userMapper.selectOne(user);
    }
}
