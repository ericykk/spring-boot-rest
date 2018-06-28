package com.eric.base.aspect;

import com.eric.base.annotation.DynamicSwitchDataSource;
import com.eric.base.config.datasource.DatabaseContextHolder;
import com.eric.base.config.datasource.DatabaseType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 描述:控制数据源切换AOP
 *
 * @author eric
 * @create 2018-06-28 上午11:51
 */
@Aspect
@Slf4j
@Component
@Order(1)
public class HandlerDataSourceAop {
    /**
     * 定义切点
     *
     * @within 用于匹配所以持有指定注解类型内的方法
     * @annotation 用于匹配当前执行方法持有指定注解的方法
     */
    @Pointcut("@within(com.eric.base.annotation.DynamicSwitchDataSource)||@annotation(com.eric.base.annotation.DynamicSwitchDataSource)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取方法上的注解
        DynamicSwitchDataSource annotationClass = method.getAnnotation(DynamicSwitchDataSource.class);
        //获取类上面的注解
        if (annotationClass == null) {
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(DynamicSwitchDataSource.class);
            if (annotationClass == null) {
                return;
            }
        }
        //设置特殊的数据源的信息
        DatabaseType databaseType = annotationClass.dataSource();
        DatabaseContextHolder.setDatabaseType(databaseType);
        log.info("AOP动态切换数据源，className=" + joinPoint.getTarget().getClass().getName() + ",methodName=" + method.getName() + ";databaseType:" + databaseType.name());
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        DatabaseContextHolder.clear();
    }
}
