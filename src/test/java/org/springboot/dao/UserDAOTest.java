package org.springboot.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.NewsApplication;
import org.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsApplication.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void addUser() throws Exception {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("");
            user.setSalt("");
            userDAO.addUser(user);
        }
    }

    @Test
    public void queryById() throws Exception {
        int id = 1;
        User user = userDAO.queryById(id);
        System.out.println(user);
    }

    @Test
    public void queryName() throws Exception {
        String name = "USER0";
        User user = userDAO.queryName(name);
        System.out.println(user);
    }
}