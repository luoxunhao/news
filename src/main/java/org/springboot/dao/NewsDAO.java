package org.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.entity.News;

import java.util.List;

/**
 * Created by lxh on 2017/5/3.
 */
@Mapper
public interface NewsDAO {

    int addNews(News news);

    List<News> queryList(@Param("userId") int userId,
                         @Param("offset") int offset,
                         @Param("limit") int limit);

    News queryById(@Param("id") int id);

    int updateCommentCount(@Param("id") int id, @Param("count") int count);
}
