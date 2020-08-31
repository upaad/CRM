package com.hjf.service;

import com.hjf.entity.Customershare;
import java.util.List;

/**
 * (Customershare)表服务接口
 *
 * @author makejava
 * @since 2020-08-19 16:46:03
 */
public interface CustomershareService {

    /**
     * 通过ID查询单条数据
     *
     * @param shareid 主键
     * @return 实例对象
     */
    Customershare queryById(Integer shareid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Customershare> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param customershare 实例对象
     * @return 实例对象
     */
    Customershare insert(Customershare customershare);

    /**
     * 修改数据
     *
     * @param customershare 实例对象
     * @return 实例对象
     */
    Customershare update(Customershare customershare);

    /**
     * 通过主键删除数据
     *
     * @param shareid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer shareid);

    List<Customershare> queryAll(Customershare customershare);

    Customershare selectByCS(int empid, int cusid);
}