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
    int delAll(String uids);
    /*
        修改用户选课业务
     */
    int updateCourseByUid(String cid, String uid);
}
