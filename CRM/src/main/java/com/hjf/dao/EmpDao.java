package com.hjf.dao;

import com.hjf.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpDao {
    List<Emp> getAllEmp();
    int insertEmp(@Param("empid") int empid,
                  @Param("username") String username,
                  @Param("password") String password,
                  @Param("tel") String tel,
                  @Param("name") String name,
                  @Param("email") String email,
                  @Param("roleid") int roleid);
    int updateEmp(@Param("empid") int empid,
                  @Param("username") String username,
                  @Param("password") String password,
                  @Param("tel") String tel,
                  @Param("name") String name,
                  @Param("email") String email,
                  @Param("roleid") int roleid);
    int deleteEmp(@Param("empid") int empid);
    Emp selectById(@Param("empid") int empid);
    List<Emp> selectByIdUname(@Param("empid") int empid,
                              @Param("username") String username);
    List<Emp> selectByLimit(@Param("first") int first,
                            @Param("maxResult") int maxResult);
    Emp selectByName(@Param("username") String username);
    Emp login(@Param("username")String username,@Param("password") String password);
    List<Emp> select(Emp emp);
    void resetpwd();
    List<Emp> selectByName2(String username);
    List<Emp> selectByList(@Param("emp") Emp emp,@Param("roleInfo") String roleInfo);
}
