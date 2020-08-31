package com.hjf.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharacterFilter implements Filter {

    public CharacterFilter(){
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //字符集设置
        //处理服务端到客户端乱码
        response.setContentType("text/css;charset=utf-8");
        //处理客户端到服务端乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        //正常放行
        HttpServletResponse resp =(HttpServletResponse) response;
        resp.setHeader("Pragma","no-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setDateHeader("Expires",-1);


        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
