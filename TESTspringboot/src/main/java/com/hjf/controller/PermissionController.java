package com.hjf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.entity.Permission;
import com.hjf.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Permission)表控制层
 *
 * @author makejava
 * @since 2020-08-19 16:44:31
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Permission selectOne(Integer id) {
        return this.permissionService.queryById(id);
    }

    @RequiresPermissions("perm:add")
    @PostMapping("/insert")
    public Map insert(@RequestBody Permission permission) {
        Map map = new HashMap();
        Permission p = permissionService.insert(permission);
        if (p != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("perm:upd")
    @PostMapping("/update")
    public Map update(@RequestBody Permission permission) {
        Map map = new HashMap();
        Permission p = permissionService.update(permission);
        if (p != null) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @RequiresPermissions("perm:del")
    @GetMapping("/delete")
    public Map delete(Permission permission) {
        Map map = new HashMap();
        List<Permission> list = permissionService.queryPermRp(permission.getPermissionid());
        if (list.size() > 0) {
            map.put("status", "error");
        } else {
            permissionService.deleteById(permission.getPermissionid());
            map.put("status", "success");
        }
        return map;
    }

    @RequiresPermissions("perm:list")
    @GetMapping("select")
    public Map select(Permission permission, int page, int size) {
        Map map = new HashMap();
        PageHelper.startPage(page,size);
        List<Permission> list = permissionService.queryAll(permission);
        PageInfo<Permission> pi = new PageInfo<>(list);
        map.put("total",pi.getTotal());
        map.put("rows",list);
        return map;
    }

    @GetMapping("selectbyperm")
    public List<Permission> selectByPerm(Permission permission) {
        return permissionService.queryAll(permission);
    }

    @GetMapping("/selectbyinfo")
    public Map selectByInfo(String txt_pinfo) {
        Map map = new HashMap();
        Permission permission = new Permission();
        permission.setPinfo(txt_pinfo);
        List<Permission> list = permissionService.queryAll(permission);
        map.put("valid",false);
        if (list.size() == 0){
            map.put("valid",true);
        }
        return map;
    }

    @GetMapping("/selectbyid")
    public Map selectById(int txt_permid) {
        Map map = new HashMap();
        Permission p = permissionService.queryById(txt_permid);
        map.put("valid",false);
        if (p != null){
            map.put("valid",true);
        }
        return map;
    }
}