package com.ujiuye.dao;

import com.ujiuye.entity.CourseUser;

import java.util.ArrayList;
import java.util.List;

public interface CourseUserDao {
    /*查询选课总数*/
    int findCourseUserByCount();
    /*查询选课信息*/
    List<CourseUser> findCourseUserByLimit(int cpage, int size);
    /*根据uid删除课程信息*/
    int deleteByUid(String uid);
    /*根据uid和课程cid根据用户选课信息*/
    int updateCourseByUid(String cid, String uid);
}
