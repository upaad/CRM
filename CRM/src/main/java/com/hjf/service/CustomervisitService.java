package com.hjf.service;

import com.hjf.entity.Customervisit;
import java.util.List;

/**
 * (Customervisit)表服务接口
 *
 * @author makejava
 * @since 2020-08-19 16:46:12
 */
public interface CustomervisitService {

    /**
     * 通过ID查询单条数据
     *
     * @param visitid 主键
     * @return 实例对象
     */
    Customervisit queryById(Integer visitid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Customervisit> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param customervisit 实例对象
     * @return 实例对象
     */
    Customervisit insert(Customervisit customervisit);

    /**
     * 修改数据
     *
     * @param customervisit 实例对象
     * @return 实例对象
     */
    Customervisit update(Customervisit customervisit);

    /**
     * 通过主键删除数据
     *
     * @param visitid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer visitid);

    List<Customervisit> queryAll(Customervisit customervisit);
}