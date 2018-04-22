package com.goshine.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goshine.mapper.DemoBaseMapper;
import com.goshine.mapper.UserMapper;
import com.goshine.service.UserService;
import com.goshine.web.enums.UserStatus;
import dto.BaseModel;
import dto.PageQuery;
import dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

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
