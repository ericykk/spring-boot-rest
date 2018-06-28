package com.eric.base.model;

import lombok.Data;

import java.util.Date;

@Data
public class VoteUser {
    private Long id;

    private String name;

    private Date createAt;

    private String phoneNo;
}