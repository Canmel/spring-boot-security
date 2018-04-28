package com.goshine.service.serviceImpl;

import com.goshine.core.base.DemoBaseMapper;
import com.goshine.mapper.MenuMapper;
import com.goshine.mapper.RoleMenuMapper;
import com.goshine.service.MenuService;
import com.goshine.service.RoleService;
import com.goshine.web.enums.MenuLevel;
import dto.Menu;
import dto.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

    @Autowired
    public MenuMapper menuMapper;

    @Override
    public DemoBaseMapper getMapper() {
        return menuMapper;
    }

    @Autowired
    public RoleMenuMapper roleMenuMapper;

    @Override
    public List<Menu> getTopMenus() {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("level", MenuLevel.ONELEVEL.getStatus());
        List<Menu> menuList = getMapper().selectByExample(example);
        return menuList;
    }

    @Override
    public List<Menu> getSubMenus() {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("level", MenuLevel.TWOLEVEL.getStatus());
        List<Menu> menuList = getMapper().selectByExample(example);
        return menuList;
    }

    @Override
    public List<Menu> getMenusByRoleId(int id) {
        List<RoleMenu> rms = getRoleMenusByRoleId(id);
        if (rms.size() == 0 ){
            return new ArrayList<Menu>();
        }
        List<Integer> ids = new ArrayList<>();
        for (RoleMenu rm : rms) {
            if (!ObjectUtils.isEmpty(rm.getMenuId())) {
                ids.add(rm.getMenuId());
            }
        }
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return getMapper().selectByExample(example);
    }

    private List getRoleMenusByRoleId(int id) {
        Example example = new Example(RoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", id);
        return roleMenuMapper.selectByExample(example);
    }
}
