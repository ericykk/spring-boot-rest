package com.eric.base.controller;

import com.eric.base.core.JsonResult;
import com.eric.base.core.token.TokenManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 *
 * @author eric
 * @create 2018-06-21 下午2:52
 */
@RestController
public class ApiController {
    @Resource
    private TokenManager tokenManager;

    @GetMapping(value = "/createToken")
    public JsonResult createToken(String userName, HttpServletResponse response) {
        String apiToken = tokenManager.createToken(userName);
        Cookie tokenCookie = new Cookie("api-token", apiToken);
        response.addCookie(tokenCookie);
        return new JsonResult().success(apiToken);
    }


    @GetMapping(value = "/api/validateToken")
    public JsonResult validateToken() {
        return new JsonResult().success();
    }
}
