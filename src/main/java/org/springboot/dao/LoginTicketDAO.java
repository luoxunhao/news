package org.springboot.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.entity.LoginTicket;

/**
 * Created by lxh on 2017/3/6.
 */
@Mapper
public interface LoginTicketDAO {

    int addTicket(LoginTicket loginTicket);

    LoginTicket selectByTicket(String ticket);

    int updateStatus(@Param("ticket") String ticket, @Param("status") int status);

}
