package com.hjf.service.impl;

import com.hjf.entity.Customer;
import com.hjf.dao.CustomerDao;
import com.hjf.entity.Emp;
import com.hjf.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Customer)表服务实现类
 *
 * @author makejava
 * @since 2020-08-19 16:45:49
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cusid 主键
     * @return 实例对象
     */
    @Override
    public Customer queryById(Integer cusid) {
        return this.customerDao.queryById(cusid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Customer> queryAllByLimit(int offset, int limit) {
        return this.customerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer insert(Customer customer) {
        this.customerDao.insert(customer);
        return customer;
    }

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer update(Customer customer) {
        this.customerDao.update(customer);
        return this.queryById(customer.getCusid());
    }

    /**
     * 通过主键删除数据
     *
     * @param cusid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer cusid) {
        return this.customerDao.deleteById(cusid) > 0;
    }

    @Override
    public List<Customer> queryAll(Customer customer) {
        return customerDao.queryAll(customer);
    }

    @Override
    public Customer queryByCus(int empid, int cusid) {
        return queryByCus(empid,cusid);
    }

    @Override
    public int selectNum(int empid) {
        return customerDao.selectNum(empid);
    }

    @Override
    public List<Customer> queryAllBySel(Customer customer) {
        return customerDao.queryAllBySel(customer);
    }

}