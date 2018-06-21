package com.eric.base.core.token;

/**
 * 描述:token接口
 *
 * @author eric
 * @create 2018-06-21 上午11:10
 */
public interface TokenManager {
    String createToken(String username);

    boolean checkToken(String token);
}
