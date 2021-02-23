package com.ujiuye.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ujiuye.dao.CourseDao;
import com.ujiuye.entity.Course;
import com.ujiuye.utils.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    QueryRunner qr = new QueryRunner(dataSource);

    @Override
    public int saveCourse(Course c) {
        String sql = "insert into course values(null,?,?,?,?,?,?,?,?)";
        try {
            return qr.update(sql, c.getCourseName(), c.getDescs(), c.getCourseType(), c.getCourseImage(), c.getCourseVideo(), c.getCoursePrice(), c.getStatus(), new Date());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int findCourseByCount(StringBuilder sb, ArrayList<Object> list) {
        try {
            //集合中装的就是符合条件的所有数据
            List<Course> course_list = qr.query(sb.toString(),
                    new BeanListHandler<Course>(Course.class), list.toArray());
            return course_list.size();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Course> findCourseBySelectAndLimit(int cpage, int size, StringBuilder sb, ArrayList<Object> list) {
        //计算起始页
        int start = (cpage - 1) * size;
        //拼接分页sql
        sb.append(" limit ?,? ");
        list.add(start);
        list.add(size);
        try {
            return qr.query(sb.toString(), new BeanListHandler<Course>(Course.class), list.toArray());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteByCid(String cid) {
        String sql = "delete from course where cid =?";
        try {
            return qr.update(sql, cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateCourseByCid(Course c) {
        String sql = "update course set courseName=?,descs=?,courseType=?,courseImage=?,courseVideo=?,coursePrice=?,status=?,createTime=? where cid =?";
        try {
            return qr.update(sql, c.getCourseName(), c.getDescs(), c.getCourseType(), c.getCourseImage(), c.getCourseVideo(), c.getCoursePrice(), c.getStatus(), new Date(), c.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Course findCourseByCid(String cid) {

        String sql = "select * from course where cid =?";
        try {
            return qr.query(sql, new BeanHandler<Course>(Course.class), cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> findAllCourse() {
        String sql ="select * from course";
        try {
            return  qr.query(sql,new BeanListHandler<Course>(Course.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
