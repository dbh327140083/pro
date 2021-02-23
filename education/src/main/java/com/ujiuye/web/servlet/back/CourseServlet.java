package com.ujiuye.web.servlet.back;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.HttpRequest;
import com.ujiuye.entity.Course;
import com.ujiuye.entity.ResultVO;
import com.ujiuye.service.CourseService;
import com.ujiuye.service.impl.CourseServiceImpl;
import com.ujiuye.service.impl.CourseUserServiceImp;
import com.ujiuye.utils.FileUploadUtils;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.utils.PageBean;
import com.ujiuye.utils.SubStringFileName;
import com.ujiuye.web.servlet.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet("/courses")
public class CourseServlet extends BaseServlet {

    CourseService service = new CourseServiceImpl();
    ResultVO vo = null;

    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取想看的页码
        String currentPage = request.getParameter("currentPage");
        //获取每页显示条数
        String pageSize = request.getParameter("pageSize");
        //获取查询参数
        String courseName = request.getParameter("courseName");
        //调用业务组装PageBean
        PageBean<Course> pb = service.findByPage(currentPage, pageSize, courseName);
        //转json
        String asString = JsonUtils.toJson(pb, response);
        response.getWriter().print(asString);
    }

    public void delAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cids = request.getParameter("cids");
        int row = service.delAll(cids);
        if (row > 0) {
            vo = new ResultVO(200, "批量删除成功", null);
        } else {
            vo = new ResultVO(500, "批量删除成功", null);
        }
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }

    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = FileUploadUtils.fileUploadMethod(request);
        String url = "http://localhost/img/" + name;
        if (name.contains("jpg") || name.contains("png") || name.contains("gif")) {
            vo = new ResultVO(200, "上传图片成功", url);
        } else {
            vo = new ResultVO(200, "上传视频成功", url);
        }
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }

    public void RemoveFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("fileName");
        fileName = SubStringFileName.subFileName(fileName);
        File file = new File("D:/xm_upload/" + fileName);
        file.delete();
        if (fileName.contains("jpg") || fileName.contains("png") || fileName.contains("gif")) {
            vo = new ResultVO(200, "删除图片成功", 1);
        } else {
            vo = new ResultVO(200, "删除视频成功", 2);
        }
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }

    public void addCourse(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Course course = new Course();
        BeanUtils.populate(course, map);
        int row = service.addCourse(course);
        ResultVO vo = null;
        if (row > 0) {
            vo = new ResultVO(200, "新增课程成功", null);
        } else {
            vo = new ResultVO(500, "新增课程失败", null);
        }
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }

    public void updateCourse(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Course course = new Course();
        BeanUtils.populate(course, map);
        String courseImage1 = request.getParameter("courseImage1");
        String courseVideo1 = request.getParameter("courseVideo1");
        //设置新的图片名称
        if (courseImage1 != null && !courseVideo1.equals("")) {
            course.setCourseImage(SubStringFileName.subFileName(courseImage1));
        }
        //设置新的视频名称
        if (courseVideo1 != null && !courseVideo1.equals("")) {
            course.setCourseVideo(SubStringFileName.subFileName(courseVideo1));
        }
        int row = service.updateCourse(course);
        ResultVO vo = null;
        if (row > 0) {
            vo = new ResultVO(200, "修改课程成功", null);
        } else {
            vo = new ResultVO(500, "修改课程失败", null);
        }
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }

    public void findAllCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Course> list = service.findAllCourse();
        vo = new ResultVO(200, "查询可惜信息成功", list);
        String asString = JsonUtils.toJson(vo, response);
        response.getWriter().print(asString);
    }


}
