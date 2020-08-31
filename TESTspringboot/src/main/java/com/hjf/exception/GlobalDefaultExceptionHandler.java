package com.hjf.exception;


import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 1、新建一个Class,这里取名为GlobalDefaultExceptionHandler
 * 2、在class上添加注解，@ControllerAdvice;
 * 3、在class中添加一个方法
 * 4、在方法上添加@ExcetionHandler拦截相应的异常信息；
 * 5、如果返回的是View -- 方法的返回值是ModelAndView;
 * 6、如果返回的是String或者是Json数据，那么需要在方法上添加@ResponseBody注解.
 *
 *
 * @author gc
 * @version v.0.1
 * @date 2018年8月18日
*/

//已经配置了就不用配置这个了
//@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Map defaultExceptionHandler(HttpServletRequest req, Exception e){
        Map map = new HashMap();
        map.put("status","unath");
        System.out.println("运行了权限不足的全局异常处理");
        return map;
    }
}
