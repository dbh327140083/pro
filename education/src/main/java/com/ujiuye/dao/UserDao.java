package com.ujiuye.dao;

import com.ujiuye.entity.Course;
import com.ujiuye.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface UserDao{
    /*
        查询符合条件的用户信息条数
     */
    int findUserByCount(StringBuilder sb, ArrayList<Object> list);
    /*
        查询符合条件的用户信息
    */
    List<User> findUserBySelectAndLimit(int cpage, int size, StringBuilder sb, ArrayList<Object> list);
    /*
        新增用户
     */
    int saveUser(User user);
    /*
       更新用户
     */
    int updateUserByUid(User user);
    /*
        根据uid删除用户信息
     */
    int deleteByUid(String uid);
    /*
       根据用户名和密码查询用户信息
     */
    User findByUserNameAndPassword(String username, String password);
    /*
        根据用户uid查询用户信息
     */
    User findUserByUid(String uid);
}
