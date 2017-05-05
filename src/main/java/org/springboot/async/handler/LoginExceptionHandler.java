package org.springboot.async.handler;


import org.springboot.async.EventModel;
import org.springboot.async.EventType;
import org.springboot.entity.Message;
import org.springboot.service.MailService;
import org.springboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LoginExceptionHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    MailService mailService;

    @Override
    public void doHandle(EventModel model) {
        //判断是否登录异常 TODO
        Message message = new Message();
        message.setToId(model.getActorId());
        message.setContent("你上次的登陆IP异常");
        // SYSTEM ACCOUNT
        message.setFromId(3);
        message.setCreatedDate(new Date());
        messageService.addMessage(message);

        Map<String, Object> map = new HashMap();
        map.put("username", model.getExt("username"));
        mailService.sendWithHTMLTemplate(model.getExt("1215070400@qq.com"), "登陆异常",
                "mails/welcome.html", map);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LOGIN);
    }
}
