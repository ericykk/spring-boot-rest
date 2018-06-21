package com.eric.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 *
 * @author eric
 * @create 2018-06-21 下午3:37
 */
public class CookieUtil {

    /**
     * 返回指定cookieName的value值
     *
     * @param cookieName
     * @param request
     * @return
     */
    public static String getCookieValue(String cookieName, HttpServletRequest request) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                value = cookie.getValue();
                break;
            }
        }
        return value;
    }
}
