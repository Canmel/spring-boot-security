package com.goshine.service.serviceImpl;

import com.goshine.core.base.DemoBaseMapper;
import com.goshine.mapper.UserMapper;
import com.goshine.mapper.UserRoleMapper;
import com.goshine.service.UserService;
import dto.RoleMenu;
import dto.User;
import dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public UserRoleMapper userRoleMapper;

    @Override
    public boolean updateRoles(int userId, List<Integer> ids) {
        return deleteRelationUserRole(userId) && addRelationUserRole(userId, ids);
    }

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

    private boolean deleteRelationUserRole(int roleId) {
        Example example = new Example(RoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        if (userRoleMapper.deleteByExample(example) >= 0) {
            return true;
        }
        return false;
    }

    private boolean addRelationUserRole(int userId, List<Integer> menuIds) {
        List<UserRole> urs = new ArrayList<>();
        for (Integer menuId : menuIds) {
            urs.add(new UserRole(userId, menuId));
        }
        if (userRoleMapper.insertBatch(urs) >= 0) {
            return true;
        }
        return false;
    }
}
