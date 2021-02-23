package com.ujiuye.service.impl;

import com.ujiuye.dao.CourseDao;
import com.ujiuye.dao.impl.CourseDaoImpl;
import com.ujiuye.entity.Course;
import com.ujiuye.service.CourseService;
import com.ujiuye.utils.PageBean;
import com.ujiuye.utils.SubStringFileName;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
   CourseDao dao= new CourseDaoImpl();
    @Override
    public PageBean<Course> findByPage(String currentPage, String pageSize,String courseName) {
        //如果没有传递值 设置的默认值
        if(currentPage==null || currentPage.trim().equals("")) {
            currentPage="1";
        }
        if(pageSize==null || pageSize.trim().equals("")) {
            pageSize="3";
        }
        PageBean<Course> pb = new PageBean<Course>();
        //字符串转int
        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        pb.setCurrentPage(cpage);
        pb.setPageSize(size);
        //定义可变sql
        StringBuilder sb = new StringBuilder("select * from Course where 1=1   ");

        //创建一个集合存储参数
        ArrayList<Object> list = new ArrayList<Object>();
        //如果课程名称不为空
        if(courseName!=null && !courseName.trim().equals("")) {
            sb.append("  and courseName like ?  ");
            list.add("%"+courseName+"%");
        }
        //查询符合条件的总条数
        int totalCount =dao.findCourseByCount(sb,list);
        pb.setTotalCount(totalCount);
        //查询符合条件的分页数据
        List<Course> medicine_list =dao.findCourseBySelectAndLimit(cpage,size,sb,list);
        pb.setList(medicine_list);
        //总页数
        int totalPage =totalCount%size==0?totalCount/size:totalCount/size+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public int addCourse(Course course) {
        //截取文件名
        course.setCourseImage(SubStringFileName.subFileName(course.getCourseImage()));
        course.setCourseVideo(SubStringFileName.subFileName(course.getCourseVideo()));
        return dao.saveCourse(course);
    }

    @Override
    public int delAll(String cids) {
        String[] ids = cids.split(",");
        int index =0;
        for (String cid : ids) {
           int row = dao.deleteByCid(cid);
           if(row>0){
               index++;
           }
        }
        return index;
    }

    @Override
    public int updateCourse(Course course) {

        return dao.updateCourseByCid(course);
    }

    @Override
    public List<Course> findAllCourse() {

        return dao.findAllCourse();
    }

}
