package com.hjf.service;

import com.hjf.entity.Customer;
import com.hjf.entity.Emp;

import java.util.List;

/**
 * (Customer)表服务接口
 *
 * @author makejava
 * @since 2020-08-19 16:45:49
 */
public interface CustomerService {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    Customer queryById(Integer cusid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Customer> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer insert(Customer customer);

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer update(Customer customer);

    /**
     * 通过主键删除数据
     *
     * @param cusid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cusid);

    List<Customer> queryAll(Customer customer);

    Customer queryByCus(int empid, int cusid);

    int selectNum(int empid);

    List<Customer> queryAllBySel(Customer customer);
}