package com.easydao.earlybird.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.easydao.earlybird.mapper.UserMapper;
import com.easydao.earlybird.bean.User;
import com.easydao.earlybird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
     *@author  czg
     *@date 2018/9/28 11:17
     *@description 列表 分页
     */
    @Override
    public PageInfo<User> getUserList(Map<String, Object> paramMap) {
        int pageSize = (int)paramMap.get("pageSize");
        int pageNo = (int) paramMap.get("pageNo");
        PageHelper.startPage(pageNo, pageSize);
        List<User> userList = userMapper.getUserList(paramMap);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public void addUser(User user) {
        user.setCreate_date(new Date());
        userMapper.addUser(user);
    }


    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }


    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public void editPasswd(User user) {
        userMapper.editPasswd(user);
    }

    @Override
    public void editUserPhone(User user) {
        userMapper.editUserPhone(user);
    }

    @Override
    public void editUserEmail(User user) {
        userMapper.editUserEmail(user);
    }


}
