package com.ujiuye.service.impl;

import com.ujiuye.dao.UserDao;
import com.ujiuye.dao.impl.UserDaoImpl;
import com.ujiuye.entity.Course;
import com.ujiuye.entity.User;
import com.ujiuye.service.UserService;
import com.ujiuye.utils.PageBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public User findByUP(String username, String password) {
        return dao.findByUserNameAndPassword(username,password);
    }

    @Override
    public int saveUser(User user) {

        return dao.saveUser(user);
    }

    @Override
    public User findByUid(String uid) {
        return null;
    }

    @Override
    public int updateUser(User user) {
        return dao.updateUserByUid(user);
    }

    @Override
    public int delMoreUser(String uids) {
        String[] ids = uids.split(",");
        int index = 0;
        for (String uid : ids) {
            int row = dao.deleteByUid(uid);
            if (row > 0) {
                index++;
            }
        }
        return index;
    }

    @Override
    public PageBean<User> findAllByPage(String currentPage, String pageSize, String username) {
        //如果没有传递值 设置的默认值
        if (currentPage == null || currentPage.trim().equals("")) {
            currentPage = "1";
        }
        if (pageSize == null || pageSize.trim().equals("")) {
            pageSize = "3";
        }
        PageBean<User> pb = new PageBean<User>();
        //字符串转int
        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        pb.setCurrentPage(cpage);
        pb.setPageSize(size);
        //定义可变sql
        StringBuilder sb = new StringBuilder("select * from user where 1=1   ");
        //创建一个集合存储参数
        ArrayList<Object> list = new ArrayList<Object>();
        //如果传递的药品名称
        if (username != null && !username.trim().equals("")) {
            sb.append("  and username like ?  ");
            list.add("%" + username + "%");
        }
        //查询符合条件的总条数
        int totalCount = dao.findUserByCount(sb, list);
        pb.setTotalCount(totalCount);
        //查询符合条件的分页数据
        List<User> user_list = dao.findUserBySelectAndLimit(cpage, size, sb, list);
        pb.setList(user_list);
        //总页数
        int totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }
}

