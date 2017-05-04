package org.springboot.dao;


import org.apache.ibatis.annotations.*;
import org.springboot.entity.Comment;

import java.util.List;

@Mapper
public interface CommentDAO {

    int addComment(Comment comment);

    List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);
}
