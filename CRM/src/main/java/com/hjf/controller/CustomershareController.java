package com.hjf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.entity.Customer;
import com.hjf.entity.Customershare;
import com.hjf.entity.Customervisit;
import com.hjf.entity.Emp;
import com.hjf.service.CustomershareService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Customershare)表控制层
 *
 * @author makejava
 * @since 2020-08-19 16:46:03
 */
@RestController
@RequestMapping("customershare")
public class CustomershareController {
    /**
     * 服务对象
     */
    @Resource
    private CustomershareService customershareService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Customershare selectOne(Integer id) {
        return this.customershareService.queryById(id);
    }

    @RequiresPermissions("cuss:add")
    @PostMapping("/insert")
    public Map insert(@RequestBody Customershare customershare) {
        Map map = new HashMap();
        Customershare cs = customershareService.insert(customershare);
        if (cs != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("cuss:upd")
    @PostMapping("/update")
    public Map update(@RequestBody Customershare customershare) {
        Map map = new HashMap();
        Customershare cs = customershareService.update(customershare);
        if (cs != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("cuss:del")
    @GetMapping("/delete")
    public void delete(int shareid) {
        customershareService.deleteById(shareid);
    }

    @RequiresPermissions("cuss:list")
    @GetMapping("/select")
    public Map select(Customershare customershare, int page, int size) {
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp) subject.getPrincipal();
        if ("emp".equals(emp.getRoleInfo())){
            System.out.println("分享查询是emp角色id为--->" + emp.getEmpid());
            customershare.setEmpid(emp.getEmpid());
        }
        Map map = new HashMap();
        PageHelper.startPage(page,size);
        List<Customershare> list = customershareService.queryAll(customershare);
        PageInfo<Customershare> pi = new PageInfo<>(list);
        map.put("total",pi.getTotal());
        map.put("rows",list);
        return map;
    }

    @GetMapping("/selectShare")
    public Map selectShare(Customershare customershare) {
        Map map = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp) subject.getPrincipal();
        if (emp.getEmpid() == customershare.getEmpid()) {
            map.put("status",1);
            return map;
        }
        Customershare c = customershareService.selectByCS(customershare.getEmpid(),customershare.getCusid());
        map.put("status",2);
        if (c == null) {
            map.put("status",0);
        }
        return map;
    }
}