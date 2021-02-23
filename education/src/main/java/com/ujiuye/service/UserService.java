package com.ujiuye.service;

import com.ujiuye.entity.User;
import com.ujiuye.utils.PageBean;

public interface UserService {
    /*
        登录业务
     */
    User findByUP(String username, String password);
    /*
        新增用户业务
     */
    int saveUser(User user);

    User findByUid(String uid);
    /*
      更新用户业务
     */
    int updateUser(User user);
    /*
        批量删除用户业务
     */
    int delMoreUser(String ids);
    /*
        分页+组合条件查询用户业务
     */
    PageBean<User> findAllByPage(String currentPage, String pageSize, String username);

}
