package com.ujiuye.dao;

import com.ujiuye.entity.Course;
import com.ujiuye.utils.PageBean;

import java.util.ArrayList;
import java.util.List;

public interface CourseDao {
    /*
      保存课程
     */
    int saveCourse(Course course);
    /*
      查询符合条件的总条数
     */
    int findCourseByCount(StringBuilder sb, ArrayList<Object> list);
    /*
       查询符合条件的数据
     */
    List<Course> findCourseBySelectAndLimit(int cpage, int size, StringBuilder sb, ArrayList<Object> list);
    /*
        根据课程号删除课程信息
     */
    int deleteByCid(String cid);
    /*
        根据课程号更新课程信息
     */
    int updateCourseByCid(Course course);
    /*
        根据课程号查询课程信息
     */
    Course findCourseByCid(String cid);
    /*
        查询所有课程
     */
    List<Course> findAllCourse();
}
