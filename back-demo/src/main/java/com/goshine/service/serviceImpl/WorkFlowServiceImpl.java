package com.goshine.service.serviceImpl;

import com.goshine.core.base.DemoBaseMapper;
import com.goshine.mapper.UserMapper;
import com.goshine.mapper.UserRoleMapper;
import com.goshine.mapper.WorkFlowMapper;
import com.goshine.service.UserService;
import com.goshine.service.WorkFlowService;
import dto.RoleMenu;
import dto.User;
import dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkFlowServiceImpl extends BaseServiceImpl implements WorkFlowService {

    @Autowired
    private WorkFlowMapper mapper;

    @Override
    public DemoBaseMapper getMapper() {
        return mapper;
    }
}
