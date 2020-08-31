package com.hjf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.entity.Customervisit;
import com.hjf.entity.Emp;
import com.hjf.service.CustomervisitService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Customervisit)表控制层
 *
 * @author makejava
 * @since 2020-08-19 16:46:12
 */
@RestController
@RequestMapping("customervisit")
public class CustomervisitController {
    /**
     * 服务对象
     */
    @Resource
    private CustomervisitService customervisitService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Customervisit selectOne(Integer id) {
        return this.customervisitService.queryById(id);
    }

    @RequiresPermissions("cusv:add")
    @PostMapping("/insert")
    public Map insert(@RequestBody Customervisit customervisit) {
        Map map = new HashMap();
        System.out.println(customervisit);
        Customervisit cv = customervisitService.insert(customervisit);
        if (cv != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("cusv:upd")
    @PostMapping("/update")
    public Map update(@RequestBody Customervisit customervisit) {
        Map map = new HashMap();
        System.out.println(customervisit);
        Customervisit cv = customervisitService.update(customervisit);
        if (cv != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("cusv:del")
    @GetMapping("/delete")
    public void delete(Customervisit customervisit) {
        customervisitService.deleteById(customervisit.getVisitid());
    }

    @RequiresPermissions("cusv:list")
    @GetMapping("select")
    public Map select(Customervisit customervisit, int page, int size) {
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp) subject.getPrincipal();
        System.out.println("从subject获取到emp————》" + emp);
        if ("emp".equals(emp.getRoleInfo())){
            System.out.println("拜访查询是emp角色id为--->" + emp.getEmpid());
            customervisit.setEmpid(emp.getEmpid());
        }
        Map map = new HashMap();
        PageHelper.startPage(page,size);
        List<Customervisit> list = customervisitService.queryAll(customervisit);
        PageInfo<Customervisit> pi = new PageInfo<>(list);
        map.put("total",pi.getTotal());
        map.put("rows",list);
        return map;
    }

}