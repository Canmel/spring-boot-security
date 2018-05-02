package com.goshine.service;

import dto.User;

import java.util.List;

public interface UserService extends BaseService {
    User loadUserByUserName(String userName);
    boolean updateRoles(int userId, List<Integer> ids);
}
