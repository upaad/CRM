package com.hjf.dao;

import com.hjf.entity.Customervisit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Customervisit)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-19 16:46:12
 */
@Repository
public interface CustomervisitDao {

    /**
     * 通过ID查询单条数据
     *
     * @param visitid 主键
     * @return 实例对象
     */
    Customervisit queryById(Integer visitid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Customervisit> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param customervisit 实例对象
     * @return 对象列表
     */
    List<Customervisit> queryAll(Customervisit customervisit);

    /**
     * 新增数据
     *
     * @param customervisit 实例对象
     * @return 影响行数
     */
    int insert(Customervisit customervisit);

    /**
     * 修改数据
     *
     * @param customervisit 实例对象
     * @return 影响行数
     */
    int update(Customervisit customervisit);

    /**
     * 通过主键删除数据
     *
     * @param visitid 主键
     * @return 影响行数
     */
    int deleteById(Integer visitid);

}