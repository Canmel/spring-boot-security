package com.goshine.service;

import dto.Role;

import java.util.List;

public interface RoleService extends BaseService {
    boolean updateMemus(int roleId, List<Integer> ids);

    List<Role> all();

    List<Role> getRoleByUserId(int id);
}
