package com.hjf.controller;

import com.hjf.entity.Emp;
import com.hjf.service.EmpService;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EmpService empService;

    @GetMapping("/login")
    @ResponseBody
    public Map login(Emp emp) {
        Map map = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(emp.getUsername(), emp.getPassword());
        Emp user = empService.login(emp);
        map.put("status",false);
        try {
            subject.login(token);
            map.put("status",true);
            map.put("role",user.getRoleInfo());
            map.put("userid",user.getEmpid());
            map.put("name",user.getName());
            System.out.println("---->登陆成功");
        } catch (AuthenticationException e) {
//            e.printStackTrace();
            System.out.println("---->登陆失败");
        }
        return map;
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("----->注销");
        return "redirect:/login.html";
    }


    @PostMapping("/register")
    @ResponseBody
    public Map register(@RequestBody Emp emp) {
        Map map = new HashMap();
        int i = empService.insertEmp(emp);
        if (i > 0) {
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }



}
