package com.hjf.service.impl;

import com.hjf.entity.Customershare;
import com.hjf.dao.CustomershareDao;
import com.hjf.service.CustomershareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Customershare)表服务实现类
 *
 * @author makejava
 * @since 2020-08-19 16:46:03
 */
@Service("customershareService")
public class CustomershareServiceImpl implements CustomershareService {
    @Resource
    private CustomershareDao customershareDao;

    /**
     * 通过ID查询单条数据
     *
     * @param shareid 主键
     * @return 实例对象
     */
    @Override
    public Customershare queryById(Integer shareid) {
        return this.customershareDao.queryById(shareid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Customershare> queryAllByLimit(int offset, int limit) {
        return this.customershareDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param customershare 实例对象
     * @return 实例对象
     */
    @Override
    public Customershare insert(Customershare customershare) {
        this.customershareDao.insert(customershare);
        return customershare;
    }

    /**
     * 修改数据
     *
     * @param customershare 实例对象
     * @return 实例对象
     */
    @Override
    public Customershare update(Customershare customershare) {
        this.customershareDao.update(customershare);
        return this.queryById(customershare.getShareid());
    }

    /**
     * 通过主键删除数据
     *
     * @param shareid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer shareid) {
        return this.customershareDao.deleteById(shareid) > 0;
    }

    @Override
    public List<Customershare> queryAll(Customershare customershare) {
        return customershareDao.queryAll(customershare);
    }

    @Override
    public Customershare selectByCS(int empid, int cusid) {
        return customershareDao.selectByCS(empid,cusid);
    }
}