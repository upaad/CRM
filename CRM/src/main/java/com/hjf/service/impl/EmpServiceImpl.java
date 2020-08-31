package com.hjf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjf.dao.EmpDao;
import com.hjf.entity.Emp;
import com.hjf.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;


    @Override
    public List<Emp> getAllEmp() {
        return empDao.getAllEmp();
    }

    @Override
    public int insertEmp(Emp emp) {
        return empDao.insertEmp(emp.getEmpid(),emp.getUsername(),emp.getPassword(),
                emp.getTel(),emp.getName(),emp.getEmail(),emp.getRoleid());
    }

    @Override
    public int updateEmp(Emp emp) {
        return empDao.updateEmp(emp.getEmpid(),emp.getUsername(),emp.getPassword(),
                emp.getTel(),emp.getName(),emp.getEmail(),emp.getRoleid());
    }

    @Override
    public int deleteEmp(Emp emp) {
        return empDao.deleteEmp(emp.getEmpid());
    }

    @Override
    public Emp selectById(int empid) {
        return empDao.selectById(empid);
    }

    @Override
    public List<Emp> selectByIdUname(Emp emp) {
        List<Emp> list = empDao.selectByIdUname(emp.getEmpid(),emp.getUsername());
        return list;
    }

    @Override
    public List<Emp> selectByLimit(int curPage, int size) {
        PageHelper.startPage(curPage,size);
        List<Emp> list = empDao.getAllEmp();
        PageInfo<Emp> pi = new PageInfo<>(list);
        return pi.getList();
    }

    @Override
    public Emp selectByName(String name) {
        return empDao.selectByName(name);
    }

    @Override
    public Emp login(Emp emp) {
        return empDao.login(emp.getUsername(),emp.getPassword());
    }

    @Override
    public List<Emp> select(Emp emp) {
        return empDao.select(emp);
    }

    @Override
    public void resetpwd() {
        empDao.resetpwd();
    }

    @Override
    public List<Emp> selectByName2(String username) {
        return empDao.selectByName2(username);
    }

    @Override
    public List<Emp> selectByList(Emp emp, String roleInfo) {
        return empDao.selectByList(emp,roleInfo);
    }
}
