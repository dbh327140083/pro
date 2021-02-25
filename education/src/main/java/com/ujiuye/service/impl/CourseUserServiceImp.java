package com.ujiuye.service.impl;
import com.ujiuye.dao.CourseUserDao;
import com.ujiuye.dao.impl.CourseUserDaoImpl;
import com.ujiuye.entity.Course;
import com.ujiuye.entity.CourseUser;
import com.ujiuye.service.CourseUserService;
import com.ujiuye.utils.PageBean;

import java.util.ArrayList;
import java.util.List;
public class CourseUserServiceImp implements CourseUserService {
    CourseUserDao dao= new CourseUserDaoImpl();
    @Override
    public PageBean<CourseUser> findByPage(String currentPage, String pageSize) {
        //如果没有传递值 设置的默认值
        if(currentPage==null || currentPage.trim().equals("")) {
            currentPage="1";
        }
        if(pageSize==null || pageSize.trim().equals("")) {
            pageSize="3";
        }
        PageBean<CourseUser> pb = new PageBean<CourseUser>();
        //字符串转int
        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        pb.setCurrentPage(cpage);
        pb.setPageSize(size);
        //查询符合条件的总条数
        int totalCount =dao.findCourseUserByCount();
        pb.setTotalCount(totalCount);
        //查询符合条件的分页数据
        List<CourseUser> medicine_list =dao.findCourseUserByLimit(cpage,size);
        pb.setList(medicine_list);
        //总页数
        int totalPage =totalCount%size==0?totalCount/size:totalCount/size+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public int delAll(String ids) {
        String[] arr = ids.split(",");
        int index =0;
        for (String id : arr) {
            int row = dao.deleteById(id);
            if(row>0){
                index++;
            }
        }
        return index;
    }

    @Override
    public int updateCourseById(String cid, String id) {

        return dao.updateCourseById(cid,id);
    }
}
