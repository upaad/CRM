package com.hjf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.entity.Rolepermission;
import com.hjf.service.RolepermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Rolepermission)表控制层
 *
 * @author makejava
 * @since 2020-08-19 16:45:26
 */
@RestController
@RequestMapping("rolepermission")
public class RolepermissionController {
    /**
     * 服务对象
     */
    @Resource
    private RolepermissionService rolepermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Rolepermission selectOne(Integer id) {
        return this.rolepermissionService.queryById(id);
    }

    @RequiresPermissions("rp:add")
    @PostMapping("/insert")
    public Map insert(@RequestBody Rolepermission rolepermission) {
        Map map = new HashMap();
        List<Rolepermission> list = rolepermissionService.queryAll(rolepermission);
        map.put("status","error");
        if (list.size() > 0) {
            return map;
        }
        Rolepermission rp = rolepermissionService.insert(rolepermission);
        if (rp != null) {
            map.put("status","success");
        }
        return map;
    }

    @RequiresPermissions("rp:upd")
    @PostMapping("/update")
    public Map update(@RequestBody Rolepermission rolepermission) {
        Map map = new HashMap();
        List<Rolepermission> list = rolepermissionService.queryAll(rolepermission);
        map.put("status","error");
        if (list.size() > 0) {
            return map;
        }
        Rolepermission rp = rolepermissionService.update(rolepermission);
        if (rp != null) {
            map.put("status","success");
        }
        return map;
    }

    @RequiresPermissions("rp:del")
    @GetMapping("/delete")
    public void delete(int rpid) {
        rolepermissionService.deleteById(rpid);
    }

    @RequiresPermissions("rp:list")
    @GetMapping("select")
    public Map select(Rolepermission rolepermission, int page, int size) {
        Map map = new HashMap();
        PageHelper.startPage(page,size);
        List<Rolepermission> list = rolepermissionService.queryAll(rolepermission);
        PageInfo<Rolepermission> pi = new PageInfo<>(list);
        map.put("total",pi.getTotal());
        map.put("rows",list);
        return map;
    }

}