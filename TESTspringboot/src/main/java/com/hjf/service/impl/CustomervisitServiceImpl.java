package com.hjf.service.impl;

import com.hjf.dao.CustomervisitDao;
import com.hjf.entity.Customervisit;
import com.hjf.service.CustomervisitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Customervisit)表服务实现类
 *
 * @author makejava
 * @since 2020-08-19 16:46:12
 */
@Service("customervisitService")
public class CustomervisitServiceImpl implements CustomervisitService {
    @Resource
    private CustomervisitDao customervisitDao;

    /**
     * 通过ID查询单条数据
     *
     * @param visitid 主键
     * @return 实例对象
     */
    @Override
    public Customervisit queryById(Integer visitid) {
        return this.customervisitDao.queryById(visitid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Customervisit> queryAllByLimit(int offset, int limit) {
        return this.customervisitDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param customervisit 实例对象
     * @return 实例对象
     */
    @Override
    public Customervisit insert(Customervisit customervisit) {
        this.customervisitDao.insert(customervisit);
        return customervisit;
    }

    /**
     * 修改数据
     *
     * @param customervisit 实例对象
     * @return 实例对象
     */
    @Override
    public Customervisit update(Customervisit customervisit) {
        this.customervisitDao.update(customervisit);
        return this.queryById(customervisit.getVisitid());
    }

    /**
     * 通过主键删除数据
     *
     * @param visitid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer visitid) {
        return this.customervisitDao.deleteById(visitid) > 0;
    }

    @Override
    public List<Customervisit> queryAll(Customervisit customervisit) {
        return customervisitDao.queryAll(customervisit);
    }
}