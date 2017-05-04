package org.springboot.service;


import org.springboot.dao.NewsDAO;
import org.springboot.entity.News;
import org.springboot.util.ToutiaoUtil;
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
    private NewsDAO newsDAO;

    public List<News> getLatestNews(int userId, int offset, int limit) {
        return newsDAO.queryList(userId, offset, limit);
    }

    public int addNews(News news) {
        newsDAO.addNews(news);
        return news.getId();
    }

    public News getById(int newsId) {
        return newsDAO.queryById(newsId);
    }

    public String saveImage(MultipartFile file) throws IOException {
        int dotPos = file.getOriginalFilename().lastIndexOf('.');
        String fileExt = file.getOriginalFilename().toLowerCase().substring(dotPos + 1);
        if (!ToutiaoUtil.isFileAllowed(fileExt)){
            return null;
        }
        String fileName = UUID.randomUUID().toString().replaceAll("_", "").toLowerCase() + "." + fileExt;
        Files.copy(file.getInputStream(),
                new File(ToutiaoUtil.LOCAL_IMAGE_SERVER_PATH  + fileName).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        return ToutiaoUtil.TOUTIAO_DOMAIN + "image/?name=" + fileName;  //将文件URL交给前端
    }

    public String[] saveImages(MultipartFile[] files) throws IOException {
        String[] fileURLS = new String[files.length];
        for (int  i = 0; i < files.length ;i++){
            fileURLS[i] = saveImage(files[i]);
        }
        return fileURLS;
    }

}
