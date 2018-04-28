package com.goshine.service;

import dto.Menu;
import dto.User;

import java.util.List;

public interface MenuService extends BaseService {
    List<Menu> getTopMenus();

    List<Menu> getSubMenus();

    List<Menu> getMenusByRoleId(int id);
}
