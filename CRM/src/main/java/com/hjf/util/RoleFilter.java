package com.hjf.util;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component("roleOrFilter")
public class RoleFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object obj) throws Exception {
        Subject subject = getSubject(request,response);
        String[] roles = (String[]) obj;
//        for (String role : roles) {
//            System.out.println("角色的--->" + role);
//        }
        if (roles == null || roles.length == 0) {
            System.out.println("没有设置角色");
            return true;
        }
        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }
}
