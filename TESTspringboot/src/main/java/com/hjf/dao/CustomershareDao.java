package com.hjf.dao;

import com.hjf.entity.Customershare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Customershare)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-19 16:46:03
 */
@Repository
public interface CustomershareDao {

    /**
     * 通过ID查询单条数据
     *
     * @param shareid 主键
     * @return 实例对象
     */
    Customershare queryById(Integer shareid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Customershare> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param customershare 实例对象
     * @return 对象列表
     */
    List<Customershare> queryAll(Customershare customershare);

    /**
     * 新增数据
     *
     * @param customershare 实例对象
     * @return 影响行数
     */
    int insert(Customershare customershare);

    /**
     * 修改数据
     *
     * @param customershare 实例对象
     * @return 影响行数
     */
    int update(Customershare customershare);

    /**
     * 通过主键删除数据
     *
     * @param shareid 主键
     * @return 影响行数
     */
    int deleteById(Integer shareid);

    Customershare selectByCS(@Param("empid") int empid, @Param("cusid") int cusid);

}