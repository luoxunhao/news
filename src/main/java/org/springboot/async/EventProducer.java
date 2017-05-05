package org.springboot.async;

import com.alibaba.fastjson.JSONObject;

import org.springboot.dao.RedisDAO;
import org.springboot.util.RedisKeyUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Service
public class EventProducer {

    @Autowired
    RedisDAO redisDAO;

    public boolean fireEvent(EventModel eventModel) {
        try {
            //事件序列化
            String json = JSONObject.toJSONString(eventModel);
            String key = RedisKeyUtil.getEventQueueKey();
            //将事件放入队列中
            redisDAO.lpush(key, json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
