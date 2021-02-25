package com.ujiuye.service;

import com.ujiuye.entity.CourseUser;
import com.ujiuye.utils.PageBean;

public interface CourseUserService {
    /*
       分页查询用户选课业务
     */
    PageBean<CourseUser> findByPage(String currentPage, String pageSize);
    /*
     批量删除业务
     */
    int delAll(String ids);
    /*
        修改用户选课业务
     */
    int updateCourseById(String cid, String id);
}
