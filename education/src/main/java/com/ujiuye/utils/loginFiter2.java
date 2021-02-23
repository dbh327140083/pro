package com.ujiuye.utils;

import com.ujiuye.entity.ResultVO;
import com.ujiuye.entity.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/*
  权限拦截
 */
@WebFilter("/*")
public class loginFiter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //获取请求的url地址
        String url = request.getRequestURL().toString();
        //获取请求的方法
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        //获取session中的对象
        User user = (User) session.getAttribute("user");
        if(user==null && !url.contains("/login") && !url.contains("/code") && !"login".equals(method) && !url.contains("loginOut")){
            ResultVO vo = new ResultVO(500, "请先登录后操作", null);
            String s = JsonUtils.toJson(vo, response);
            response.getWriter().print(s);
            return;
        }
        //放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
