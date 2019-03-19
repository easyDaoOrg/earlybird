package com.easydao.earlybird.mapper;

import com.easydao.earlybird.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {

    List<User> getUserList(Map<String, Object> paramMap);

    void addUser(User user);

    void deleteUser(Long id);

    User findUserByName(String username);

    void editPasswd(User user);

    void editUserPhone(User user);

    void editUserEmail(User user);
}
