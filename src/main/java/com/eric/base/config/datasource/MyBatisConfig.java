package com.eric.base.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author eric
 * @create 2018-06-27 下午4:15
 */
@Configuration
@MapperScan(basePackages = "com.eric.base.mapper")
public class MyBatisConfig {

    @Primary
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.oracle.datasource")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     *
     * @return
     */
    @Primary
    @Bean
    public DynamicDataSource dataSource(@Qualifier("mysqlDataSource") DataSource mysqlDataSource,
                                        @Qualifier("oracleDataSource") DataSource oracleDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(3);
        targetDataSources.put(DatabaseType.MYSQL, mysqlDataSource);
        targetDataSources.put(DatabaseType.ORACLE, oracleDataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        // 保存所有数据源
        dataSource.setTargetDataSources(targetDataSources);
        // 设置默认数据源
        dataSource.setDefaultTargetDataSource(mysqlDataSource);
        return dataSource;
    }


    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
        // 指定数据源
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dynamicDataSource);
        // 配置mybatis
        fb.setTypeAliasesPackage("com.eric.base.mapper");
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
