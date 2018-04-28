package com.goshine.mapper;

import com.goshine.core.base.DemoBaseMapper;
import dto.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenuMapper extends DemoBaseMapper<Menu>{
}