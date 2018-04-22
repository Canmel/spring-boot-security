package com.goshine.service;

import com.github.pagehelper.PageInfo;
import dto.BaseModel;

public interface BaseService {

    public PageInfo query(BaseModel model);
}
