package org.springboot.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.NewsApplication;
import org.springboot.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsApplication.class)
public class NewsDaoTest {
    @Autowired
    private NewsDao newsDao;

    @Test
    public void addNews() throws Exception {
        Random random = new Random();
        for (int i = 0; i < 11; ++i) {
            News news = new News();
            news.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000*3600*5*i);
            news.setCreatedDate(date);
            news.setImage(String.format("http://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
            news.setLikeCount(i+1);
            news.setUserId(2);
            news.setTitle(String.format("TITLE{%d}", i));
            news.setLink(String.format("http://www.nowcoder.com/%d.html", i));
            newsDao.addNews(news);
        }
    }

    @Test
    public void queryList() throws Exception {
        int userId = 2;
        int offset = 3;
        int limit = 2;
        List<News> newsList = newsDao.queryList(userId, offset, limit);
        for (News news : newsList){
            System.out.println(news);
        }
    }

    @Test
    public void queryById() throws Exception {
        int id = 2;
        News news = newsDao.queryById(id);
        System.out.println(news);
    }

}