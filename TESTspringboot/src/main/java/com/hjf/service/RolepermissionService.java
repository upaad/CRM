package com.hjf.service;

import com.hjf.entity.Rolepermission;

import java.util.List;

/**
 * (Rolepermission)表服务接口
 *
 * @author makejava
 * @since 2020-08-19 16:45:26
 */
public interface RolepermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param rpid 主键
     * @return 实例对象
     */
    Rolepermission queryById(Integer rpid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Rolepermission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param rolepermission 实例对象
     * @return 实例对象
     */
    Rolepermission insert(Rolepermission rolepermission);

    /**
     * 修改数据
     *
     * @param rolepermission 实例对象
     * @return 实例对象
     */
    Rolepermission update(Rolepermission rolepermission);

    /**
     * 通过主键删除数据
     *
     * @param rpid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rpid);

    List<Rolepermission> queryAll(Rolepermission rolepermission);
}