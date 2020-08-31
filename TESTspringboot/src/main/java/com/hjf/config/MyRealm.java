package com.hjf.config;

import com.hjf.entity.Emp;
import com.hjf.entity.Permission;
import com.hjf.service.EmpService;
import com.hjf.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    EmpService empService;
    @Autowired
    PermissionService permissionService;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        UsernamePasswordToken up = (UsernamePasswordToken) token;
        Emp emp = empService.selectByName(up.getUsername());
        System.out.println("----->" + emp);
        if(emp==null){
            //找不到
            return null;
        }else{
            //找到了验证密码
            return new SimpleAuthenticationInfo(emp, emp.getPassword(), getName());
        }
    }

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp) subject.getPrincipal();
        //授权角色 emp.getRoleinfo
        System.out.println("角色是----->" + emp.getRoleInfo());
        authorizationInfo.addRole(emp.getRoleInfo());
        //授权权限

        List<Permission> perms = permissionService.queryPermsByRoleId(emp.getRoleid());
        for (Permission perm : perms) {
            if(perm.getPinfo()!=null&&!"".equals(perm.getPinfo())){
                authorizationInfo.addStringPermission(perm.getPinfo());
            }
        }
        return authorizationInfo;
    }
}
