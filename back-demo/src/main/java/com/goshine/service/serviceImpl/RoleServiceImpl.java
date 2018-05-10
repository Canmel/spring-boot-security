package com.goshine.service.serviceImpl;

import com.goshine.core.base.DemoBaseMapper;
import com.goshine.mapper.RoleMapper;
import com.goshine.mapper.RoleMenuMapper;
import com.goshine.mapper.UserMapper;
import com.goshine.mapper.UserRoleMapper;
import com.goshine.service.RoleService;
import com.goshine.service.UserService;
import com.goshine.web.enums.MenuStatus;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

    @Autowired
    public RoleMapper roleMapper;

    @Autowired
    public RoleMenuMapper roleMenuMapper;

    @Autowired
    public UserRoleMapper userRoleMapper;

    @Override
    public boolean updateMemus(int roleId, List<Integer> ids) {
        return deleteRelationRoleMenu(roleId) && addRelationRoleMenu(roleId, ids);
    }

    @Override
    public List<Role> all() {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", MenuStatus.ACTIVE.getStatus());
        return getMapper().selectByExample(example);
    }

    @Override
    public List<Role> getRoleByUserId(int id) {
        List<UserRole> rms = getUserRolesByUserId(id);
        if (CollectionUtils.isEmpty(rms)) {
            return new ArrayList<Role>();
        }
        List<Integer> ids = new ArrayList<>();
        for (UserRole rm : rms) {
            if (!ObjectUtils.isEmpty(rm.getRoleId())) {
                ids.add(rm.getRoleId());
            }
        }
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return getMapper().selectByExample(example);
    }

    private List<UserRole> getUserRolesByUserId(int id) {
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", id);
        return userRoleMapper.selectByExample(example);
    }

    private boolean deleteRelationRoleMenu(int roleId) {
        Example example = new Example(RoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        if (roleMenuMapper.deleteByExample(example) >= 0) {
            return true;
        }
        return false;
    }

    private boolean addRelationRoleMenu(int roleId, List<Integer> menuIds) {
        List<RoleMenu> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            rms.add(new RoleMenu(roleId, menuId));
        }
        if (roleMenuMapper.insertBatch(rms) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public DemoBaseMapper getMapper() {
        return roleMapper;
    }
}
