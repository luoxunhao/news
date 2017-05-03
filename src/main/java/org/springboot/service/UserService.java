package org.springboot.service;

import org.springboot.dao.UserDao;
import org.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(int id) {
        return userDao.queryById(id);
    }
}
