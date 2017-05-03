package org.springboot.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.NewsApplication;
import org.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsApplication.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void addUser() throws Exception {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("");
            user.setSalt("");
            userDao.addUser(user);
        }
    }

    @Test
    public void queryById() throws Exception {
        int id = 1;
        User user = userDao.queryById(id);
        System.out.println(user);
    }
}