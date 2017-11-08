package com.ifly.transporter.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ifly.transporter.jwt.JwtFilter;

@Configuration
public class JwtConfig {
      
    @Bean  
    public FilterRegistrationBean jwtFilterRegistrationBean(){  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        JwtFilter httpBearerFilter = new JwtFilter();  
        registrationBean.setFilter(httpBearerFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/api/user/*");  //过滤user模块
//        urlPatterns.add("/swagger/*"); 
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }  
}
