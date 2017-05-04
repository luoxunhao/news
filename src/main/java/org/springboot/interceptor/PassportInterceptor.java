package org.springboot.interceptor;


import org.springboot.dao.LoginTicketDAO;
import org.springboot.dao.UserDAO;
import org.springboot.entity.HostHolder;
import org.springboot.entity.LoginTicket;
import org.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by lxh on 2017/3/7.
 */

@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private HostHolder hostHolder;

    /**
     * 执行controller之前，验票
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        //在http请求中找到名字是"ticket"的cookie，这个cookie中存着token
        if (httpServletRequest.getCookies() != null){
            for (Cookie cookie : httpServletRequest.getCookies()){
                if (cookie.getName().equals("ticket")){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (ticket != null){
            LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
            //验票，也可以通过sql语句在数据库中验票
            if (loginTicket == null || loginTicket.getExpired().before(new Date())
                    || loginTicket.getStatus() != 0){
                //httpServletRequest.getRequestDispatcher("/").forward(httpServletRequest, httpServletResponse);
                return true;  //只有return true才能正确跳转到首页
            }
            //验票成功，获取用户信息，并将用户信息存到线程局部变量中，该用户线程可以一直访问这个变量
            User user = userDAO.queryById(loginTicket.getUserId());
            hostHolder.setUser(user);
        }
        return true;  //如果验票成功，这里一定要返回true
    }

    /**
     * 在页面渲染之前执行，可以将得到的线程局部变量添加到页面中渲染。
     * 实现后端和前端的交互
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && hostHolder.getUser() != null){
            modelAndView.addObject("user",hostHolder.getUser());
        }
    }

    /**
     * 清理工作
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }
}
