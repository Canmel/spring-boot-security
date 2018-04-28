package com.goshine.mapper;

import com.goshine.core.base.DemoBaseMapper;
import dto.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMenuMapper extends DemoBaseMapper<RoleMenu> {
    int insertBatch(List<RoleMenu> rms);
}