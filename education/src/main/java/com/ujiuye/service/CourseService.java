package com.ujiuye.service;

import com.ujiuye.entity.Course;
import com.ujiuye.utils.PageBean;

import java.util.List;

public interface CourseService {
    /*
        分页业务
     */
    PageBean<Course>findByPage(String currentPage, String pageSize,String courseName);
    /*
     新增课程业务
     */
    int addCourse(Course course);
    /*
       删除课程业务
     */
    int delAll(String cids);
    /*
       更新课程业务
     */
    int updateCourse(Course course);
    /*
       查询所有课程
     */
    List<Course> findAllCourse();
}
