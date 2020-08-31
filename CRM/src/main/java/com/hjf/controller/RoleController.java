package com.hjf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.entity.*;
import com.hjf.service.EmpService;
import com.hjf.service.RoleService;
import com.hjf.service.RolepermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Role)表控制层
 *
 * @author makejava
 * @since 2020-08-19 16:41:29
 */
@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    @Autowired
    private EmpService empService;

    @Autowired
    private RolepermissionService rolepermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Role selectOne(Integer id) {
        return this.roleService.queryById(id);
    }

    @RequiresPermissions("role:add")
    @PostMapping("/insert")
    public Map insert(@RequestBody Role role) {
        Map map = new HashMap();
        Role r = roleService.insert(role);
        if (r != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("role:upd")
    @PostMapping("/update")
    public Map update(@RequestBody Role role) {
        Map map = new HashMap();
        Role r = roleService.update(role);
        if (r != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("role:del")
    @GetMapping("/delete")
    public Map delete(int roleid) {
        Map map = new HashMap();
        List<Role> list = roleService.queryEmpRp(roleid);
        if (list.size() > 0) {
            map.put("status", "error");
        } else {
            roleService.deleteById(roleid);
            map.put("status", "success");
        }
        return map;

    }

    @GetMapping("selectbyrole")
    public List<Role> selectAll(Role role) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Emp emp = (Emp) subject.getPrincipal();
            if (emp == null){
                role.setRoleinfo("emp");
                return roleService.queryAll(role);
            } else if ("manager".equals(emp.getRoleInfo()) || "emp".equals(emp.getRoleInfo())){
                role.setRoleinfo(emp.getRoleInfo());
                return roleService.queryAll(role);
            }
            return roleService.queryAll(role);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("报异常了");
            role.setRoleinfo("emp");
            return roleService.queryAll(role);
        }
    }

    @RequiresPermissions("role:list")
    @GetMapping("select")
    public Map select(Role role, int page, int size) {
        Map map = new HashMap();
        PageHelper.startPage(page,size);
        List<Role> list = roleService.queryAll(role);
        PageInfo<Role> pi = new PageInfo<>(list);
        map.put("total",pi.getTotal());
        map.put("rows",list);
        return map;
    }

    @GetMapping("/selectbyid")
    public Map selectById(int txt_roleid) {
        Map map = new HashMap();
        Role role = roleService.queryById(txt_roleid);
        map.put("valid",false);
        if (role != null){
            map.put("valid",true);
        }
        return map;
    }

    @GetMapping("/selectbyname")
    public Map selectByName(String txt_rolename,int opt) {
        Map map = new HashMap();
        Role r = new Role();
        r.setRolename(txt_rolename);
        map.put("valid",false);
        List<Role> list = roleService.queryAll(r);
        if (opt == 1){
            if (list.size() == 0){
                map.put("valid",true);
            }
        } else if (opt == 2) {
            if (list.size() <= 1){
                map.put("valid",true);
            }
        }
        return map;
    }

    @GetMapping("/selectbyinfo")
    public Map selectByInfo(String txt_roleinfo) {
        Map map = new HashMap();
        Role r = new Role();
        r.setRolename(txt_roleinfo);
        List<Role> list = roleService.queryAll(r);
        map.put("valid",false);
        if (list.size() == 0){
            map.put("valid",true);
        }
        return map;
    }

}