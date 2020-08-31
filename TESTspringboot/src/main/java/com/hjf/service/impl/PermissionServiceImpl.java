package com.hjf.service.impl;

import com.hjf.dao.PermissionDao;
import com.hjf.entity.Permission;
import com.hjf.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Permission)表服务实现类
 *
 * @author makejava
 * @since 2020-08-19 16:44:31
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param permissionid 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Integer permissionid) {
        return this.permissionDao.queryById(permissionid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Permission> queryAllByLimit(int offset, int limit) {
        return this.permissionDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Permission> queryPermsByRoleId(int roleid) {
        return permissionDao.queryPermsByRoleId(roleid);
    }

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission permission) {
        this.permissionDao.insert(permission);
        return permission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionDao.update(permission);
        return this.queryById(permission.getPermissionid());
    }

    /**
     * 通过主键删除数据
     *
     * @param permissionid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer permissionid) {
        return this.permissionDao.deleteById(permissionid) > 0;
    }

    @Override
    public List<Permission> queryAll(Permission permission) {
        return permissionDao.queryAll(permission);
    }

    @Override
    public List<Permission> queryPermRp(Integer permissionid) {
        return permissionDao.queryPermRp(permissionid);
    }
}