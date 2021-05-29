package com.ujiuye.handler;

import com.alibaba.fastjson.JSON;
import com.ujiuye.commons.ResultCode;
import com.ujiuye.commons.ResultData;
import com.ujiuye.utils.util.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: lcj
 * @Date: 2020/10/27 10:56
 * @Description: 认证用户无权访问
 * @Version: 0.0.1
 */
@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(new Result(false,"您没有权限访问！！！")));
    }
}
