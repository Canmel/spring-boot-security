package com.goshine.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goshine.core.base.DemoBaseMapper;
import com.goshine.service.BaseService;
import dto.BaseModel;
import dto.PageQuery;
import dto.User;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl implements BaseService {


    @Override
    public PageInfo query(BaseModel model) {
        PageQuery pageQuery = new PageQuery();
        Example example = new Example(model.getClass());
        Example.Criteria criteria = example.createCriteria();
        buildExample(model, criteria);
        PageInfo pageInfo = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()).doSelectPageInfo(() -> getMapper().selectByExample(example));
        return pageInfo;
    }

    @Override
    public boolean create(BaseModel model) {
        boolean flag = false;
        int result = getMapper().insert(model);
        if(result > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public BaseModel details(BaseModel model) {
        return (BaseModel) getMapper().selectOne(model);
    }

    @Override
    public boolean update(BaseModel model) {
        boolean flag = false;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", model.getId());
        int result = getMapper().updateByExampleSelective(model, example);
        if(result > 0){
            flag = true;
        }
        return flag;
    }

    /**
     * 构建Example
     * 循环遍历model的属性并获取，属性不为空加入example
     *
     * @param model
     * @return
     */
    public Example.Criteria buildExample(BaseModel model, Example.Criteria criteria) {
        Field[] fileds = model.getClass().getDeclaredFields();
        for (Field field : fileds) {
            String fieldName = field.getName();
            Object fieldValue = getFieldValueByName(fieldName, model);
            if (!ObjectUtils.isEmpty(fieldValue) && notInParams(fieldName)) {
                String className = field.getType().getName();
                if (className.equals("java.lang.String")) {
                    criteria.andLike(fieldName, "%" + fieldValue + "%");
                } else if (className.equals("java.lang.Integer")) {
                    criteria.andEqualTo(fieldName, (Integer) fieldValue);
                }
            }
            if (fieldName.equals("status") && ObjectUtils.isEmpty(fieldValue)) {
                criteria.andNotEqualTo("status", 0);
            }
        }
        return criteria;
    }

    public Example doBuild(Example example) {

        return example;
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断属性不包括分页信息
     *
     * @return
     */
    private boolean notInParams(String filedName) {
        boolean flag = true;
        List params = new ArrayList();
        params.add("currentPage");
        params.add("pageSize");
        if (params.contains(filedName)) {
            flag = false;
        }
        return flag;
    }

    public abstract DemoBaseMapper getMapper();
}
