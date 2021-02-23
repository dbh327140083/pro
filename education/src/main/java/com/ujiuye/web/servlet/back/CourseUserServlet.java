package com.ujiuye.web.servlet.back;

import com.ujiuye.entity.Course;
import com.ujiuye.entity.CourseUser;
import com.ujiuye.entity.ResultVO;
import com.ujiuye.service.CourseService;
import com.ujiuye.service.CourseUserService;
import com.ujiuye.service.impl.CourseServiceImpl;
import com.ujiuye.service.impl.CourseUserServiceImp;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.utils.PageBean;
import com.ujiuye.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cs")
public class CourseUserServlet extends BaseServlet {

    CourseUserService service = new CourseUserServiceImp();
    ResultVO vo = null;
    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取想看的页码
        String currentPage = request.getParameter("currentPage");
        //获取每页显示条数
        String pageSize = request.getParameter("pageSize");
        //调用业务组装PageBean
        PageBean<CourseUser> pb = service.findByPage(currentPage, pageSize);
        //转json
        String asString = JsonUtils.toJson(pb, response);
        response.getWriter().print(asString);
    }

    public void delAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uids = request.getParameter("uids");
        int row = service.delAll(uids);
        if (row > 0) {
            vo = new ResultVO(200, "批量删除成功", null);
        } else {
            vo = new ResultVO(500, "批量删除成功", null);
        }
        String asString =JsonUtils.toJson(vo,response);
        response.getWriter().print(asString);
    }
    public void updateCourseByUid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cid = request.getParameter("cid");
        String uid = request.getParameter("uid");
        int row = service.updateCourseByUid(cid,uid);
        if (row > 0) {
            vo = new ResultVO(200, "修改选课成功", null);
        } else {
            vo = new ResultVO(500, "修改选课成功", null);
        }
        String asString =JsonUtils.toJson(vo,response);
        response.getWriter().print(asString);
    }

}
