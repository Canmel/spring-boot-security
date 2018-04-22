package com.goshine.service;

import dto.User;

public interface UserService extends BaseService {

    User loadUserByUserName(String userName);
}
