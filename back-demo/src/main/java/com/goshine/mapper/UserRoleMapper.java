package com.goshine.mapper;

import com.goshine.core.base.DemoBaseMapper;
import dto.RoleMenu;
import dto.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRoleMapper extends DemoBaseMapper<UserRole>{
    int insertBatch(List<UserRole> urs);
}