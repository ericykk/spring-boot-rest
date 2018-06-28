package com.eric.base.config.datasource;


/**
 * 描述:保存一个线程安全的DatabaseType容器
 *
 * @author eric
 * @create 2018-06-27 下午3:43
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType databaseType) {
        // 移除原有数据源配置
        CONTEXT_HOLDER.remove();
        // 新增数据源配置
        CONTEXT_HOLDER.set(databaseType);
    }


    public static DatabaseType getDatabaseType() {
        return CONTEXT_HOLDER.get();
    }


    /**
     * 清除数据源配置 采用默认数据源
     */
    public static void clear() {
        CONTEXT_HOLDER.remove();
    }
}
