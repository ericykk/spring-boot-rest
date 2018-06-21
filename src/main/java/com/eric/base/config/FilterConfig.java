package com.eric.base.config;

import com.eric.base.core.filter.TokenAuthorFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:拦截器配置
 *
 * @author eric
 * @create 2018-06-21 下午2:12
 */
@Configuration
public class FilterConfig {


    @Bean
    public TokenAuthorFilter tokenAuthorFilterBean() {
        return new TokenAuthorFilter();
    }

    //注册filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(tokenAuthorFilterBean());
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/api/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
