package com.goshine.web.controller;

import com.github.pagehelper.PageInfo;
import com.goshine.core.base.R;
import com.goshine.service.BaseService;
import dto.BaseModel;
import dto.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

public abstract class BaseController {

    public PageInfo query(BaseModel modal) {
        return getService().query(modal);
    }

    public boolean create(BaseModel model) {
        return getService().create(model);
    }

    public BaseModel details(BaseModel model) {
        return getService().details(model);
    }

    public boolean update(BaseModel model) {
        return getService().update(model);
    }

    public boolean delete(BaseModel model) {
        return getService().delete(model);
    }

    protected R generateErrorResp(BindingResult errors) {
        if (errors.hasErrors()) {
            StringBuffer msg = new StringBuffer();
            errors.getAllErrors().forEach(error -> msg.append(error.getDefaultMessage()));
            return R.error(msg.toString());
        } else {
            return null;
        }
    }

    public abstract BaseService getService();

}
