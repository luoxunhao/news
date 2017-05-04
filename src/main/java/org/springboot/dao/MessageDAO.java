package org.springboot.dao;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.entity.Message;

import java.util.List;


@Mapper
public interface MessageDAO {

    int addMessage(Message message);

    List<Message> getConversationDetail(@Param("conversationId") String conversationId, @Param("offset") int offset, @Param("limit") int limit);

    List<Message> getConversationList(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

    int getConversationUnReadCount(@Param("userId") int userId, @Param("conversationId") String conversationId);

}