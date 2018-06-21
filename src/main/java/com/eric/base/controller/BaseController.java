package com.eric.base.controller;

import com.eric.base.core.JsonResult;
import com.eric.base.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author eric
 * @create 2018-06-20 下午2:39
 */
@RestController
public class BaseController {

    @GetMapping(value = "/index")
    public String index() {
        return "hello world!";
    }

    @GetMapping(value = "/getUser")
    public List<User> getUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(122000083049775104L, "张三", "123", new Date()));
        userList.add(new User(122000083049775105L, "李四", null, new Date()));
        return userList;
    }


    @PostMapping(value = "/validateUser")
    public JsonResult validateUser(@Valid User user) {
        System.out.println(user.getName());
        return new JsonResult().success();
    }
}
