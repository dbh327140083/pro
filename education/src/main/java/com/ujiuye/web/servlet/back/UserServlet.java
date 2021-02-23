package com.ujiuye.web.servlet.back;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujiuye.entity.Course;
import com.ujiuye.entity.ResultVO;
import com.ujiuye.entity.User;
import com.ujiuye.service.CourseService;
import com.ujiuye.service.UserService;
import com.ujiuye.service.impl.CourseServiceImpl;
import com.ujiuye.service.impl.UserServiceImpl;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.utils.PageBean;
import com.ujiuye.web.servlet.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/users")
public class UserServlet extends BaseServlet {
    UserService service = new UserServiceImpl();
    ResultVO vo = null;
    // 校验登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取用户名和密码  shift+alt+上下  移动代码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("imageCode");
        HttpSession session = request.getSession();
        String verCode = (String) session.getAttribute("verCode");
        if (code.equalsIgnoreCase(verCode)) {
            // 2. 根据用户名和密码 查询
            User user = service.findByUP(username, password);
            // 3. 判断
            if (user != null) {
                // 判断角色 和 状态
                if (user.getRole() == 1 && user.getStatus() == 1) {
                    // 登录成功
                    session.setAttribute("user", user);
                    vo = new ResultVO(4, "登录成功", null);
                    String s = JsonUtils.toJson(vo, response);
                    response.getWriter().print(s);
                } else {
                    vo = new ResultVO(3, "用户没有权限或未启用", null);
                    String s = JsonUtils.toJson(vo, response);
                    response.getWriter().print(s);
                }
            } else {
                vo = new ResultVO(2, "用户名或密码错误", null);
                String s = JsonUtils.toJson(vo, response);
                response.getWriter().print(s);
            }

        } else {
            ResultVO vo = new ResultVO(1, "验证码错误", null);
            String s = JsonUtils.toJson(vo, response);
            response.getWriter().print(s);

        }
    }

    //获取用户信息
    public void getUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            vo = new ResultVO(500, "获取用户信息失败请先登录", null);
        }else{
            vo = new ResultVO(200, "获取用户信息成功", user);
        }
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }


    // 退出登录
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        vo = new ResultVO(200, "退出登录成功", null);
        String s = JsonUtils.toJson(vo, response);
        response.getWriter().print(s);
    }

    public void findAllByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取想看的页码
        String currentPage = request.getParameter("currentPage");
        //获取每页显示条数
        String pageSize = request.getParameter("pageSize");
        //获取查询参数
        String username = request.getParameter("username");
        //调用业务组装PageBean
        PageBean<User> pb = service.findAllByPage(currentPage, pageSize, username);
        //转json
        String asString = JsonUtils.toJson(pb, response);
        response.getWriter().print(asString);
    }

    public void addUser(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user, map);
        int row = service.saveUser(user);
        ResultVO vo = null;
        if (row > 0) {
            vo = new ResultVO(200, "新增用户成功", null);
        } else {
            vo = new ResultVO(500, "新增用户失败", null);
        }
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user, map);
        int row = service.updateUser(user);
        ResultVO vo = null;
        if (row > 0) {
            vo = new ResultVO(200, "更新用户成功", null);
        } else {
            vo = new ResultVO(500, "更新用户失败", null);
        }
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }

    public void delAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uids = request.getParameter("uids");
        int row = service.delMoreUser(uids);
        if (row > 0) {
            vo = new ResultVO(200, "批量删除成功", null);
        } else {
            vo = new ResultVO(500, "批量删除成功", null);
        }
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }
}
