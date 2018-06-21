package com.eric.base.core.token;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:token维护
 *
 * @author eric
 * @create 2018-06-21 上午11:45
 */
@Component
public class TokenManagerImpl implements TokenManager {

    private static Map<String, String> tokenMap = new ConcurrentHashMap<>();


    /**
     * 根据用户名创建token
     *
     * @param userName
     * @return
     */
    @Override
    public String createToken(String userName) {
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, userName);
        return token;
    }


    /**
     * 验证token是否合法
     *
     * @param token
     * @return
     */
    @Override
    public boolean checkToken(String token) {
        return StringUtils.isNotBlank(token) && tokenMap.containsKey(token);
    }
}
