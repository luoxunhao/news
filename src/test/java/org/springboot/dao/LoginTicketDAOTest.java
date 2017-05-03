package org.springboot.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.NewsApplication;
import org.springboot.entity.LoginTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by lxh on 2017/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsApplication.class)
public class LoginTicketDAOTest {
    @Autowired
    private LoginTicketDAO loginTicketDAO;

    @Test
    public void addTicket() throws Exception {
        Random random = new Random();
        for (int i = 0; i < 11; ++i) {
            Date date = new Date();
            LoginTicket ticket = new LoginTicket();
            ticket.setStatus(0);
            ticket.setUserId(i+1);
            ticket.setExpired(date);
            ticket.setTicket(String.format("TICKET%d", i+1));
            loginTicketDAO.addTicket(ticket);
        }
    }

    @Test
    public void selectByTicket() throws Exception {
        String ticket = "TICKET8";

    }

    @Test
    public void updateStatus() throws Exception {

    }

}