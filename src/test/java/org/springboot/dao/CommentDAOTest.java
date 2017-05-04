package org.springboot.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.NewsApplication;
import org.springboot.entity.Comment;
import org.springboot.entity.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsApplication.class)
public class CommentDAOTest {

    @Autowired
    private CommentDAO commentDAO;

    @Test
    public void addComment() throws Exception {
        Random random = new Random();
        for (int i = 0; i < 11; ++i) {
            // 给每个资讯插入3个评论
            for(int j = 0; j < 3; ++j) {
                Comment comment = new Comment();
                comment.setUserId(i+1);
                comment.setCreatedDate(new Date());
                comment.setStatus(0);
                comment.setContent("这里是一个评论啊！" + String.valueOf(j));
                comment.setEntityId(i);
                comment.setEntityType(EntityType.ENTITY_NEWS);
                commentDAO.addComment(comment);
            }
        }
    }

    @Test
    public void selectByEntity() throws Exception {
        int entityType = 1;
        int entityId = 3;
        List<Comment> comments = commentDAO.selectByEntity(entityId, entityType);
        for (Comment comment : comments){
            System.out.println(comment);
        }
    }

    @Test
    public void getCommentCount() throws Exception {
        int entityType = 1;
        int entityId = 3;
        int count = commentDAO.getCommentCount(entityId, entityType);
        System.out.println("comment count:" + count);
    }

}