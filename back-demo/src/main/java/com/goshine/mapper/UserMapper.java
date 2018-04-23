package com.goshine.mapper;

import com.goshine.core.base.DemoBaseMapper;
import dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper extends DemoBaseMapper<User> {

}