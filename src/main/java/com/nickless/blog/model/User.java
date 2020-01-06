package com.nickless.blog.model;

import lombok.Data;

/**
 * creat by nickless
 *
 * @Date 2020/1/2 13:13
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
