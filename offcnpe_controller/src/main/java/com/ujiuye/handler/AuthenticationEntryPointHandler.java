package com.ujiuye.handler;

import com.alibaba.fastjson.JSON;
import com.ujiuye.commons.ResultCode;
import com.ujiuye.commons.ResultData;
import com.ujiuye.utils.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: lcj
 * @Date: 2020/10/27 10:48
 * @Description: 匿名访问无权限处理类
 * @Version: 0.0.1
 */
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(new Result(false,"您没有权限操作！！！")));
    }
}
