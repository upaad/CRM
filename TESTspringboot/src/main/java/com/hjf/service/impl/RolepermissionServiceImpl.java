package com.hjf.service.impl;

import com.hjf.dao.RolepermissionDao;
import com.hjf.entity.Rolepermission;
import com.hjf.service.RolepermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Rolepermission)表服务实现类
 *
 * @author makejava
 * @since 2020-08-19 16:45:26
 */
@Service("rolepermissionService")
public class RolepermissionServiceImpl implements RolepermissionService {
    @Resource
    private RolepermissionDao rolepermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rpid 主键
     * @return 实例对象
     */
    @Override
    public Rolepermission queryById(Integer rpid) {
        return this.rolepermissionDao.queryById(rpid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Rolepermission> queryAllByLimit(int offset, int limit) {
        return this.rolepermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param rolepermission 实例对象
     * @return 实例对象
     */
    @Override
    public Rolepermission insert(Rolepermission rolepermission) {
        this.rolepermissionDao.insert(rolepermission);
        return rolepermission;
    }

    /**
     * 修改数据
     *
     * @param rolepermission 实例对象
     * @return 实例对象
     */
    @Override
    public Rolepermission update(Rolepermission rolepermission) {
        this.rolepermissionDao.update(rolepermission);
        return this.queryById(rolepermission.getRpid());
    }

    /**
     * 通过主键删除数据
     *
     * @param rpid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rpid) {
        return this.rolepermissionDao.deleteById(rpid) > 0;
    }

    @Override
    public List<Rolepermission> queryAll(Rolepermission rolepermission) {
        return rolepermissionDao.queryAll(rolepermission);
    }
}