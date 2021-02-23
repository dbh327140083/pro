package com.ujiuye.web.servlet.before;

import com.ujiuye.entity.Course;
import com.ujiuye.entity.CourseUser;
import com.ujiuye.entity.User;
import com.ujiuye.service.CourseService;
import com.ujiuye.service.CourseUserService;
import com.ujiuye.service.impl.CourseServiceImpl;
import com.ujiuye.service.impl.CourseUserServiceImp;
import com.ujiuye.web.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/coursesInfo")
public class CourseInfoServlet extends BaseServlet {
}
