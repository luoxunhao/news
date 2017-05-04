package org.springboot.entity;

import org.springframework.stereotype.Component;


@Component
public class HostHolder {
    //存储当前线程的变量，每一条线程只能读取自己的ThreadLocal变量
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
