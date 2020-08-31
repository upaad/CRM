package com.hjf.dao;

import com.hjf.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Permission)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-19 16:44:31
 */
@Repository
public interface PermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param permissionid 主键
     * @return 实例对象
     */
    Permission queryById(Integer permissionid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    List<Permission> queryPermsByRoleId(int roleid);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param permission 实例对象
     * @return 对象列表
     */
    List<Permission> queryAll(Permission permission);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param permissionid 主键
     * @return 影响行数
     */
    int deleteById(Integer permissionid);

    List<Permission> queryPermRp(Integer permissionid);

}