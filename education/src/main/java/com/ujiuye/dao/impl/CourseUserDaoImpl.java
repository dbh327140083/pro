package com.ujiuye.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ujiuye.dao.CourseDao;
import com.ujiuye.dao.CourseUserDao;
import com.ujiuye.dao.UserDao;
import com.ujiuye.entity.Course;
import com.ujiuye.entity.CourseUser;
import com.ujiuye.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseUserDaoImpl implements CourseUserDao {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    QueryRunner qr = new QueryRunner(dataSource);
    UserDao userDao = new UserDaoImpl();
    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public int findCourseUserByCount() {
        try {
            String sql = "select * from course_user";
            //集合中装的就是符合条件的所有数据
            List<CourseUser> course_list = qr.query(sql,
                    new BeanListHandler<CourseUser>(CourseUser.class));
            return course_list.size();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<CourseUser> findCourseUserByLimit(int cpage, int size) {
        String sql = "select * from course_user limit ?,?";
        //计算起始页
        int start = (cpage - 1) * size;
        try {
            List<CourseUser> courseUsers = qr.query(sql, new BeanListHandler<CourseUser>(CourseUser.class), start, size);
            for (CourseUser courseUser : courseUsers) {
                User user = userDao.findUserByUid(courseUser.getUid() + "");
                Course course = courseDao.findCourseByCid(courseUser.getCid() + "");
                courseUser.setUser(user);
                courseUser.setCourse(course);
            }
            return courseUsers;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteByUid(String uid) {
        String sql = "delete from course_user where uid =?";
        try {
            return qr.update(sql, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateCourseByUid(String cid, String uid) {
        String sql ="update course_user set cid =? where uid =?";
        try {
            return qr.update(sql,cid,uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
