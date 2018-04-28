package com.goshine.service.serviceImpl;

import com.goshine.core.base.DemoBaseMapper;
import com.goshine.mapper.RoleMapper;
import com.goshine.mapper.RoleMenuMapper;
import com.goshine.mapper.UserMapper;
import com.goshine.service.RoleService;
import com.goshine.service.UserService;
import dto.RoleMenu;
import dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

    @Autowired
    public RoleMapper roleMapper;

    @Autowired
    public RoleMenuMapper roleMenuMapper;

    @Override
    public boolean updateMemus(int roleId, List<Integer> ids) {
        return deleteRelationRoleMenu(roleId) && addRelationRoleMenu(roleId, ids);
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
