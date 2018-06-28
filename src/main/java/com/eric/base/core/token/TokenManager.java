package com.eric.base.core.token;

/**
 * 描述:token接口
 *
 * @author eric
 * @create 2018-06-21 上午11:10
 */
public interface TokenManager {
    /**
     * 创建token
     *
     * @param username
     * @return
     */
    String createToken(String username);

    /**
     * 检查token
     *
     * @param token
     * @return
     */
    boolean checkToken(String token);
}
