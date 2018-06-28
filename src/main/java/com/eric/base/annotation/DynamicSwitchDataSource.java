package com.eric.base.annotation;

import com.eric.base.config.datasource.DatabaseType;

import java.lang.annotation.*;

/**
 * 描述:动态切换数据源注解
 * ${DESCRIPTION}
 *
 * @author eric
 * @create 2018-06-28 上午11:46
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSource {
    DatabaseType dataSource() default DatabaseType.MYSQL;
}
