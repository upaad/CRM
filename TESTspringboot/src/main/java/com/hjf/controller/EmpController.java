package com.hjf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.entity.Customer;
import com.hjf.entity.Emp;
import com.hjf.service.CustomerService;
import com.hjf.service.EmpService;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@Log4j
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @Autowired
    private CustomerService customerService;

    private static Emp emp = new Emp();

    @RequiresPermissions("emp:add")
    @PostMapping("/insert")
    public Map insert(@RequestBody Emp emp) {
        Map map = new HashMap();
        int i = empService.insertEmp(emp);
        if (i > 0) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("emp:upd")
    @PostMapping("/update")
    public Map update(@RequestBody Emp emp) {
        Map map = new HashMap();
        int i = empService.updateEmp(emp);
        if (i > 0) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("emp:del")
    @GetMapping("/delete")
    public Map delete(Emp emp) {
        Map map = new HashMap();
        Customer c = new Customer();
        c.setEmpid(emp.getEmpid());
        List<Customer> list = customerService.queryAll(c);
        if (list.size() > 0) {
            map.put("status", "error");
        } else {
            empService.deleteEmp(emp);
            map.put("status", "success");
        }
        return map;
    }

    @RequiresPermissions("emp:list")
    @GetMapping("/select")
    public Map select(Emp emp,int page,int size) {
        Subject subject = SecurityUtils.getSubject();
        Emp e = (Emp) subject.getPrincipal();
        Map map = new HashMap();
        PageHelper.startPage(page,size);
//        List<Emp> list = empService.selectByIdUname(emp);
        List<Emp> list = empService.selectByList(emp,e.getRoleInfo());
        PageInfo<Emp> pi = new PageInfo<>(list);
        map.put("total",pi.getTotal());
        map.put("rows",list);
        return map;
    }

    @GetMapping("/selectone")
    public Emp selectOne(Emp emp) {
        return empService.selectById(emp.getEmpid());
    }

    @GetMapping("/selectById")
    public Map selectById(int txt_empid,int opt) {
        Map map = new HashMap();
        emp = empService.selectById(txt_empid);
        map.put("valid",false);
        if (opt == 0) {
            if (emp == null){
                map.put("valid",true);
            }
        } else if (opt == 1){
            if (emp != null) {
                map.put("valid",true);
            }
        }
        return map;
    }

    @GetMapping("/selectByUsername")
    public Map selectByUsername(String txt_username) {
        Map map = new HashMap();
        emp = empService.selectByName(txt_username);
        map.put("valid",false);
        if (emp == null){
            map.put("valid",true);
        }
        return map;
    }

    @GetMapping("/selectByUsername2")
    public Map selectByUsername2(String txt_username) {
        Map map = new HashMap();
        List<Emp> list = empService.selectByName2(txt_username);
        map.put("valid",false);
        if (list.size() <= 1){
            map.put("valid",true);
        }
        return map;
    }


    @GetMapping("/selectbyemp")
    public List<Emp> selectByEmp(Emp e) {
        e.setRoleid(3);
        return empService.select(e);
    }

    @GetMapping("/resetpwd")
    public void ResetPwd() {
        empService.resetpwd();
    }
}
