package com.hjf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.entity.Customer;
import com.hjf.entity.Customershare;
import com.hjf.entity.Emp;
import com.hjf.service.CustomerService;
import com.hjf.service.CustomershareService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Customer)表控制层
 *
 * @author makejava
 * @since 2020-08-19 16:45:49
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    /**
     * 服务对象
     */
    @Resource
    private CustomerService customerService;

    @Autowired
    private CustomershareService customershareService;

    @RequiresPermissions("cus:add")
    @PostMapping("/insert")
    public Map insert(@RequestBody Customer customer) {
        Map map = new HashMap();
        Customer c = customerService.insert(customer);
        System.out.println(c);
        if (c != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("cus:upd")
    @PostMapping("/update")
    public Map update(@RequestBody Customer customer) {
        Map map = new HashMap();
        Customer c = customerService.update(customer);
        if (c != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("cus:move")
    @PostMapping("/move")
    public Map move(@RequestBody Customer customer) {
        Map map = new HashMap();
        Customer c = customerService.update(customer);
        if (c != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("cus:del")
    @GetMapping("/delete")
    public void delete(int cusid) {
        customerService.deleteById(cusid);
    }

    @RequiresPermissions("cus:list")
    @GetMapping("/select")
    public Map select(Customer customer, int page, int size) {
        Map map = new HashMap();
        List<Customer> list = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp) subject.getPrincipal();
        PageHelper.startPage(page,size);
        if ("emp".equals(emp.getRoleInfo())){
            System.out.println("客户查询是emp角色id为--->" + emp.getEmpid());
            customer.setEmpid(emp.getEmpid());
            list = customerService.queryAllBySel(customer);
        } else {
            list = customerService.queryAll(customer);
        }
        PageInfo<Customer> pi = new PageInfo<>(list);
        map.put("total",pi.getTotal());
        map.put("rows",list);
        return map;
    }


    @GetMapping("/selectCus")
    public List<Customer> selectCus(Customer customer) {
        List<Customer> list = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp) subject.getPrincipal();
        if ("emp".equals(emp.getRoleInfo())){
            customer.setEmpid(emp.getEmpid());
            System.out.println("emp查他的顾客");
            list = customerService.queryAllBySel(customer);
        } else {
            System.out.println("查所有顾客");
            list = customerService.queryAll(customer);
        }
        return list;
    }

    @GetMapping("/selectbyid")
    public Map selectById(int txt_cusid) {
        Map map = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp) subject.getPrincipal();
        if ("emp".equals(emp.getRoleInfo())){
            Customer c = customerService.queryByCus(emp.getEmpid(),txt_cusid);
            Customershare cs = customershareService.selectByCS(emp.getEmpid(),txt_cusid);
            if (c != null) {
                map.put("valid", true);
            }
            if (cs != null) {
                map.put("valid", true);
            }
        } else {
            Customer c = customerService.queryById(txt_cusid);
            map.put("valid", false);
            if (c != null) {
                map.put("valid", true);
            }
        }
        return map;
    }

    @GetMapping("/selNum")
    public int selectNum(int empid) {
        return customerService.selectNum(empid);
    }



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOneById")
    public Customer selectOne(Integer id) {
        return this.customerService.queryById(id);
    }

}