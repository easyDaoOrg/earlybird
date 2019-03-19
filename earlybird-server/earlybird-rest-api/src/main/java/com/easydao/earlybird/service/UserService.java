package com.easydao.earlybird.service;

import com.github.pagehelper.PageInfo;
import com.easydao.earlybird.bean.User;

import java.util.Map;

public interface UserService {

    PageInfo<User> getUserList(Map<String, Object> paramMap);

    void addUser(User user);

    void deleteUser(Long id);

    User findUserByName(String username);

    void editPasswd(User user);

    void editUserPhone(User user);

    void editUserEmail(User user);
}
