package com.goshine.core.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface DemoBaseMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
