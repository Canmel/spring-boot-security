package com.goshine.mapper;

import dto.RoleMenu;
import java.util.List;

public interface RoleMenuMapper {
    int insert(RoleMenu record);

    List<RoleMenu> selectAll();
}