package com.nickless.blog.dto;

import lombok.Data;

/**
 * creat by nickless
 *
 * @Date 2019/12/31 11:18
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
