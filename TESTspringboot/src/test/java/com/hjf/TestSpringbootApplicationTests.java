package com.hjf;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.entity.Customer;
import com.hjf.entity.DataProperties;
import com.hjf.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TestSpringbootApplicationTests {

    @Autowired
    CustomerService customerService;

    @Autowired
    DataProperties dataProperties;

    @Test
    void contextLoads() {
        System.out.println("测试成功");
    }

    @Test
    void test01() {
        PageHelper.startPage(1,3);
        Customer customer = new Customer();
        List<Customer> list = customerService.queryAll(customer);
        PageInfo<Customer> pi = new PageInfo<>(list);
        System.out.println("total--->" + pi.getTotal());
    }

    @Test
    void test02(){
        System.out.println(dataProperties);
    }

}
