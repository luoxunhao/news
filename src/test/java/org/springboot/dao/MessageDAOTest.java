package org.springboot.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.NewsApplication;
import org.springboot.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsApplication.class)
public class MessageDAOTest {

    @Autowired
    private MessageDAO messageDAO;

    @Test
    public void getConversationList() throws Exception {
        int userId = 14;
        int offset = 0;
        int limit = 3;
        List<Message> messages = messageDAO.getConversationList(userId, offset, limit);
        System.out.println(messages);
    }

}