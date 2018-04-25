package com.goshine.service.serviceImpl;

import com.goshine.core.base.DemoBaseMapper;
import com.goshine.mapper.RoleMapper;
import com.goshine.mapper.UserMapper;
import com.goshine.service.RoleService;
import com.goshine.service.UserService;
import dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

    @Autowired
    public RoleMapper roleMapper;

    @Override
    public DemoBaseMapper getMapper() {
        return roleMapper;
    }
}
