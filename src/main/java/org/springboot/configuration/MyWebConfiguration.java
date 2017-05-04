package org.springboot.configuration;


import org.springboot.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lxh on 2017/3/7.
 */


@Component
public class MyWebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;

    //@Autowired
    //LoginRequiredInterceptor loginRequiredInterceptor;

    //@Autowired
    //AdminInterceptor adminInterceptor;

    //@Autowired
    //EncodeInterceptor encodeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        //registry.addInterceptor(encodeInterceptor);
        //registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/setting*");
        //registry.addInterceptor(adminInterceptor).addPathPatterns("/admin*");
        super.addInterceptors(registry);
    }
}
