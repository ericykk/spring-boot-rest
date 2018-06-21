package com.eric.base.config;

import com.eric.base.core.CustomObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:自定义配置
 *
 * @author eric
 * @create 2018-06-20 下午4:59
 */
@Configuration
public class AppConfig {

    /**
     * 自定义Jackson序列化对象的格式
     *
     * @return
     */
    @Bean
    public ObjectMapper configObjectMapper() {
        CustomObjectMapper objectMapper = new CustomObjectMapper();
        objectMapper.setDateFormatPattern("yyyy-mm-dd hh:mm:ss");
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        // Long类型字段格式化为字符串 避免精度丢失
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        // 初始化
        objectMapper.init();
        return objectMapper;
    }
}
