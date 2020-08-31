package com.hjf.dao;

import com.hjf.entity.Rolepermission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Rolepermission)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-19 16:45:26
 */
public interface RolepermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rpid 主键
     * @return 实例对象
     */
    Rolepermission queryById(Integer rpid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Rolepermission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param rolepermission 实例对象
     * @return 对象列表
     */
    List<Rolepermission> queryAll(Rolepermission rolepermission);

    /**
     * 新增数据
     *
     * @param rolepermission 实例对象
     * @return 影响行数
     */
    int insert(Rolepermission rolepermission);

    /**
     * 修改数据
     *
     * @param rolepermission 实例对象
     * @return 影响行数
     */
    int update(Rolepermission rolepermission);

    /**
     * 通过主键删除数据
     *
     * @param rpid 主键
     * @return 影响行数
     */
    int deleteById(Integer rpid);

}