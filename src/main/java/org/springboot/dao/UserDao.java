package org.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    int addUser(User user);  //这里传入的是一个对象，不能用@Param("user") User user

    User queryById(@Param("id") int id);

}
