package com.goshine.service;

import java.util.List;

public interface RoleService extends BaseService {
    boolean updateMemus(int roleId, List<Integer> ids);
}
