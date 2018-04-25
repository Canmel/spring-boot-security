package com.goshine.service;

import com.github.pagehelper.PageInfo;
import dto.BaseModel;

import java.util.List;

public interface BaseService {

    /**
     * 查询（分页）
     *
     * @param model 查询参数
     * @return
     */
    public PageInfo query(BaseModel model);

    /**
     * 创建一条新纪录
     *
     * @param model 插入参数
     * @return
     */
    public boolean create(BaseModel model);

    /**
     * 详情
     * 获取已知ID的具体model信息
     * id 必须
     *
     * @param model 查询参数
     * @return
     */
    public BaseModel details(BaseModel model);

    /**
     * 更新数据
     *
     * @param model 更新参数
     * @return
     */
    public boolean update(BaseModel model);

    /**
     * 删除数据
     * @param model model id primeryKey
     * @return
     */
    public boolean delete(BaseModel model);

    /**
     * 批量删除
     * @param models 批量删除对象的id的集合
     * @return
     */
    public boolean delete(List<BaseModel> models);
}
