package com.ujiuye.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ujiuye.dao.UserDao;
import com.ujiuye.entity.Course;
import com.ujiuye.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    QueryRunner qr = new QueryRunner(dataSource);

    @Override
    public int findUserByCount(StringBuilder sb, ArrayList<Object> list) {
        try {
            //集合中装的就是符合条件的所有数据
            List<User> user_list = qr.query(sb.toString(),
                    new BeanListHandler<User>(User.class), list.toArray());
            return user_list.size();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> findUserBySelectAndLimit(int cpage, int size, StringBuilder sb, ArrayList<Object> list) {
        //计算起始页
        int start = (cpage - 1) * size;
        //拼接分页sql
        sb.append(" limit ?,? ");
        list.add(start);
        list.add(size);
        try {
            return qr.query(sb.toString(), new BeanListHandler<User>(User.class), list.toArray());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int saveUser(User u) {
        String sql = "insert into user values(null,?,?,?,?,?,?,?,?,?,?)";
        try {
            return qr.update(sql, u.getName(), u.getPhone(), u.getAge(), u.getSex(), u.getUsername(), u.getPassword(), u.getStatus(), new Date(),
                    u.getRole(), u.getPicture());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateUserByUid(User u) {
        String sql = "update user set name =?,phone=?,age=?,sex=?,username=?,password=?,status=?,createTime=?,role=?,picture=? where uid =?";
        try {
            return qr.update(sql,u.getName(), u.getPhone(), u.getAge(), u.getSex(), u.getUsername(), u.getPassword(), u.getStatus(), new Date(),
                     u.getRole(), u.getPicture(),u.getUid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByUid(String uid) {
        String sql ="delete from user where uid =?";
        try {
            return qr.update(sql, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        String sql = "select * from user where username =? and password =?";

        try {
            return qr.query(sql,new BeanHandler<User>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUserByUid(String uid) {

        String sql = "select * from user where uid =?";

        try {
            return qr.query(sql,new BeanHandler<User>(User.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
