package com.hjf.service;

import com.hjf.entity.Permission;

import java.util.List;

/**
 * (Permission)表服务接口
 *
 * @author makejava
 * @since 2020-08-19 16:44:31
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param permissionid 主键
     * @return 实例对象
     */
    Permission queryById(Integer permissionid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(int offset, int limit);


    List<Permission> queryPermsByRoleId(int roleid);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param permissionid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer permissionid);

    List<Permission> queryAll(Permission permission);

    List<Permission> queryPermRp(Integer permissionid);
}