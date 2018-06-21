package com.eric.base.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述:
 *
 * @author eric
 * @create 2018-06-20 下午3:24
 */
@Data
public class User implements Serializable {
    private Long id;
    @NotBlank(message = "用户姓名不能为空，请确认！")
    private String name;
    private String password;
    private Date birthDay;

    public User(Long id, String name, String password, Date birthDay) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.birthDay = birthDay;
    }
}
