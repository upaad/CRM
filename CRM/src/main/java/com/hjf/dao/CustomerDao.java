package com.hjf.dao;

import com.hjf.entity.Customer;
import com.hjf.entity.Emp;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Customer)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-19 16:45:49
 */
public interface CustomerDao {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    Customer queryById(Integer cusid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Customer> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param customer 实例对象
     * @return 对象列表
     */
    List<Customer> queryAll(Customer customer);

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int insert(Customer customer);

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int update(Customer customer);

    /**
     * 通过主键删除数据
     *
     * @param cusid 主键
     * @return 影响行数
     */
    int deleteById(Integer cusid);

    Customer queryByCus(@Param("empid")int empid,@Param("cusid") int cusid);

    int selectNum(int empid);

    List<Customer> queryAllBySel(Customer customer);

}