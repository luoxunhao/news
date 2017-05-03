package org.springboot.service;


import org.springboot.dao.NewsDao;
import org.springboot.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * Created by nowcoder on 2016/7/2.
 */
@Service
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    public List<News> getLatestNews(int userId, int offset, int limit) {
        return newsDao.queryList(userId, offset, limit);
    }

    public int addNews(News news) {
        newsDao.addNews(news);
        return news.getId();
    }

    public News getById(int newsId) {
        return newsDao.queryById(newsId);
    }

}
