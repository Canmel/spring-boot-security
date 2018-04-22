package com.goshine.web.controller;

import com.github.pagehelper.PageInfo;
import com.goshine.service.BaseService;
import dto.BaseModel;
import dto.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {


    public PageInfo query(BaseModel modal) {
        return getService().query(modal);
    }

    public abstract BaseService getService();

}
