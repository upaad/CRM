package com.hjf.service;

import com.hjf.entity.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> getAllEmp();
    int insertEmp(Emp emp);
    int updateEmp(Emp emp);
    int deleteEmp(Emp emp);
    Emp selectById(int empid);
    List<Emp> selectByIdUname(Emp emp);
    List<Emp> selectByLimit(int curPage, int size);
    Emp selectByName(String name);
    Emp login(Emp emp);
    List<Emp> select(Emp emp);
    void resetpwd();
    List<Emp> selectByName2(String username);
    List<Emp> selectByList(Emp emp, String roleInfo);
}
