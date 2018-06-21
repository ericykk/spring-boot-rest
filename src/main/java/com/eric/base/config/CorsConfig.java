package com.eric.base.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 描述:跨域资源共享配置
 * 需要spring版本为4.2.7及以上
 *
 * @author eric
 * @create 2018-06-21 上午10:46
 */
@Configuration
public class CorsConfig {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许访问的客户端域名
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        // 允许服务端访问的客户端请求头
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // 允许访问的方法名
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        // 设置缓存
        corsConfiguration.setMaxAge(3600L);
        // 是否允许请求带有验证信息 如需要客户端域下Cookie信息 设置为true
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
